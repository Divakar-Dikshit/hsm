package com.hotel.Controller;

import com.hotel.Entity.AppUser;
import com.hotel.Entity.Property;
import com.hotel.Entity.Review;
import com.hotel.Repository.PropertyRepository;
import com.hotel.Repository.ReviewRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/review")
public class ReviewController {

    private ReviewRepository  reviewRepository;
    private PropertyRepository propertyRepository;

    public ReviewController(ReviewRepository reviewRepository, PropertyRepository propertyRepository) {
        this.reviewRepository = reviewRepository;
        this.propertyRepository = propertyRepository;
    }
//http://localhost:8080/api/v1/review?propertyId=1
    @PostMapping("/rev")
    public ResponseEntity<?> write(
            @RequestBody Review  review
            ,@RequestParam long propertyId
            ,@AuthenticationPrincipal AppUser appUser){
        Property property = propertyRepository.findById(propertyId).get();
        if (reviewRepository.existsByAppUserAndProperty(appUser, property)) {
            return new ResponseEntity<>("Review already exists",HttpStatus.OK); // 409 Conflict
        }
            review.setProperty(property);
            review.setAppUser(appUser);

        Review savedReview = reviewRepository.save(review);
       return new ResponseEntity<>(savedReview, HttpStatus.OK);
    }

    @GetMapping("/user/reviews")
        public ResponseEntity<List<Review>> getUserReviews(@AuthenticationPrincipal AppUser user){
            List<Review> reviews = reviewRepository.findByAppUser(user);
            return new ResponseEntity<>(reviews,HttpStatus.OK);
        }
}

