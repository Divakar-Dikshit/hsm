package com.hotel.payload;

import com.hotel.Entity.City;
import com.hotel.Entity.Country;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PropertyRequest {
        private String name;
        private int no_of_guest;
        private int no_of_bedrooms;
        private int no_of_bathrooms;
        private int no_of_beds;

        private Country country;
        private City city;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNo_of_guest() {
        return no_of_guest;
    }

    public void setNo_of_guest(int no_of_guest) {
        this.no_of_guest = no_of_guest;
    }

    public int getNo_of_bedrooms() {
        return no_of_bedrooms;
    }

    public void setNo_of_bedrooms(int no_of_bedrooms) {
        this.no_of_bedrooms = no_of_bedrooms;
    }

    public int getNo_of_bathrooms() {
        return no_of_bathrooms;
    }

    public void setNo_of_bathrooms(int no_of_bathrooms) {
        this.no_of_bathrooms = no_of_bathrooms;
    }

    public int getNo_of_beds() {
        return no_of_beds;
    }

    public void setNo_of_beds(int no_of_beds) {
        this.no_of_beds = no_of_beds;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }
// Getters and setters


}
