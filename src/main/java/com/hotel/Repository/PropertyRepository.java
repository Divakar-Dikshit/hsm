package com.hotel.Repository;

import com.hotel.Entity.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Long> {
    @Query("select p from Property p JOIN p.city c JOIn p.country co where c.name=:name or co.name=:name")
    List<Property> SearchHotels(@Param("name") String name);
}

