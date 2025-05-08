package com.hotel.UserService;

import com.hotel.Entity.AppUser;
import com.hotel.Repository.AppUserRepository;
import com.hotel.payload.LoginDto;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final AppUserRepository userRepository;
    private JWTService jwtService;

    public UserService(AppUserRepository userRepository, JWTService jwtService) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
    }

    public String verifyLogin(LoginDto dto) {
        Optional<AppUser> opUser = userRepository.findByusername(dto.getUsername());
        if (opUser.isPresent()) {
            AppUser appUser = opUser.get();
            if (BCrypt.checkpw(dto.getPassword(), appUser.getPassword())) {
                return jwtService.generateToken(appUser.getUsername());
            } else {
                return "Invalid username/password";  // Invalid password
            }
        }
        return "Invalid username/password";  // Username not found
    }

}
