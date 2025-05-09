package com.hotel.Repository;

import com.hotel.Entity.AppUser;
import com.hotel.Entity.Property;
import com.hotel.Entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findByAppUser(AppUser user);

    // Fixed method name
    boolean existsByAppUserAndProperty(AppUser user, Property property);
}
