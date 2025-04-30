package com.Hotel.Repository;

import com.Hotel.Entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    Optional<AppUser>findByusername(String username);
    Optional<AppUser> findByemail(String email);

}