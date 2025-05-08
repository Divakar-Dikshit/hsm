package com.hotel.config;

import com.hotel.Entity.AppUser;
import com.hotel.Repository.AppUserRepository;
import com.hotel.UserService.JWTService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
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

        if (token != null && token.startsWith("Bearer ")) {
            String jwtToken = token.substring(8,token.length()-1); // Start right after "Bearer "

            String username = jwtService.getUsername(jwtToken);
            System.out.println(username);

            Optional<AppUser> opUsername = userRepository.findByusername(username);//it go to database verify the token
          if(opUsername.isPresent()){
              AppUser appUser = opUsername.get();
              UsernamePasswordAuthenticationToken authenticationToken=
                  new UsernamePasswordAuthenticationToken(appUser,null, Collections.singleton(new SimpleGrantedAuthority(appUser.getRole())));// we create object and give user details this include complete user details
              authenticationToken.setDetails(new WebAuthenticationDetails(request));//url details
              SecurityContextHolder.getContext().setAuthentication(authenticationToken);//granting him access
          }
        }
        filterChain.doFilter(request,response); // this code should be last otherwise yuo got forbidden etc

    }
}