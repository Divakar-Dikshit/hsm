package com.Hotel.config;

import com.Hotel.Entity.AppUser;
import com.Hotel.Repository.AppUserRepository;
import com.Hotel.UserService.JWTService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;


@Component
public class
JWTFilter extends OncePerRequestFilter {

    private JWTService jwtService ;
    private AppUserRepository userRepository;

    public JWTFilter(JWTService jwtService, AppUserRepository userRepository) {
        this.jwtService = jwtService;
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        //url & token automatically comes to this class

        String token = request.getHeader("Authorization");
        System.out.println(token);
        filterChain.doFilter(request,response);
        if (token != null && token.startsWith("Bearer ")) {
            String jwtToken= token.substring(8, token.length()-1);
            String username = jwtService.getUsername(jwtToken);
            //System.out.println(username);
            Optional<AppUser> opUsername = userRepository.findByusername(username);//it go to database verify the token
          if(opUsername.isPresent()){

          }
        }

    }
}