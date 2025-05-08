package com.hotel.UserService;

import com.hotel.Entity.City;
import com.hotel.Entity.Country;
import com.hotel.Entity.Property;
import com.hotel.payload.PropertyRequest;
import com.hotel.Repository.CityRepository;
import com.hotel.Repository.CountryRepository;
import com.hotel.Repository.PropertyRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PropertyService {

    private static PropertyRepository propertyRepository;
    private CityRepository cityRepository;
    private CountryRepository countryRepository;


    public PropertyService(PropertyRepository propertyRepository, CityRepository cityRepository, CountryRepository countryRepository) {
        this.propertyRepository = propertyRepository;
        this.cityRepository = cityRepository;
        this.countryRepository = countryRepository;

    }

    // Create Property
    public Property createProperty(PropertyRequest request) {

        Property property = new Property();

        // Set basic attributes
        property.setName(request.getName());
        property.setNo_of_guest(request.getNo_of_guest());
        property.setNo_of_bedrooms(request.getNo_of_bedrooms());
        property.setNo_of_bathrooms(request.getNo_of_bathrooms());
        property.setNo_of_beds(request.getNo_of_beds());

       
        // Fetch and set Country
        Long countryId = request.getCountry().getId();
        Country country = getCountryById(countryId);
        property.setCountry(country);

        // Fetch and set City
        Long cityId = request.getCity().getId();
        City city = getCityById(cityId);
        property.setCity(city);

        Property savedproperty = propertyRepository.save(property);
        savedproperty.getCountry().setName(country.getName());
        savedproperty.getCity().setName(city.getName());

        return savedproperty;
    }

    // Helper method to fetch Country
    private Country getCountryById(Long id) {
        return countryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Country not found with id: " + id));
    }

    // Helper method to fetch City
    private City getCityById(Long id) {
        return cityRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("City not found with id: " + id));
    }

    public List<Property> getAllProperties() {
        return propertyRepository.findAll();
    }

    public static List<Property> searchHotels(String name) {
        List<Property> properties = propertyRepository.SearchHotels(name);
        return properties;
    }


}