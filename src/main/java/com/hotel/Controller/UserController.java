package com.hotel.Controller;

import com.hotel.Entity.AppUser;
import com.hotel.Repository.AppUserRepository;
import com.hotel.UserService.UserService;
import com.hotel.payload.LoginDto;
import com.hotel.payload.TokenDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private  AppUserRepository userRepository;
    private  UserService userService;

    public UserController(AppUserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<?> createUser(@RequestBody AppUser appUser) {

        Optional<AppUser> opUsername = userRepository.findByusername(appUser.getUsername());
        if (opUsername.isPresent()) {
            return new ResponseEntity<>("Username already exists", HttpStatus.BAD_REQUEST);
        }

        Optional<AppUser> opEmail = userRepository.findByemail(appUser.getEmail());
        if (opEmail.isPresent()) {
            return new ResponseEntity<>("Email already exists", HttpStatus.BAD_REQUEST);
        }

        // Encrypt and save
        String encryptedPassword = BCrypt.hashpw(appUser.getPassword(), BCrypt.gensalt(8));
        appUser.setPassword(encryptedPassword); // Encrypt password directly on user object
       appUser.setRole("ROLE_USER");
        AppUser savedUser = userRepository.save(appUser);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginDto dto) {
        String token = userService.verifyLogin(dto);
        if (token != null) {
            TokenDto tokenDto = new TokenDto();
            tokenDto.setToken(token);
            tokenDto.setType("JWT");
            return new ResponseEntity<>(tokenDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Invalid username/password", HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/signup-property-owner")
    public ResponseEntity<?> createPropertyOwner(@RequestBody AppUser appUser) {

        Optional<AppUser> opUsername = userRepository.findByusername(appUser.getUsername());
        if (opUsername.isPresent()) {
            return new ResponseEntity<>("Username already exists", HttpStatus.BAD_REQUEST);
        }

        Optional<AppUser> opEmail = userRepository.findByemail(appUser.getEmail());
        if (opEmail.isPresent()) {
            return new ResponseEntity<>("Email already exists", HttpStatus.BAD_REQUEST);
        }

        // Encrypt and save
        String encryptedPassword = BCrypt.hashpw(appUser.getPassword(), BCrypt.gensalt(8));
        appUser.setPassword(encryptedPassword); // Encrypt password directly on user object
        appUser.setRole("ROLE_OWNER");
        AppUser savedUser = userRepository.save(appUser);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }
}
