package com.example.user_location_ambula.services;


import com.example.user_location_ambula.entities.UserLocation;
import com.example.user_location_ambula.repositories.UserRepository;
import com.example.user_location_ambula.utils.CalculateDistance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    /*
    * Service Layer
    * All the business logic for the APIs are implemented here
    *
    * UserService implements UserDetailsService to override loadUserByUsername method,
    * Using this method we will authenticate the user as ADMIN or as READER
    *
    * It implements HTTP Basic authentication system where we attach Authorization header
    *   -   Authorization ::    "Basic YWRtaW46YWRtaW4xMjM=" value as ADMIN
    *   -   Authorization ::    "Basic cmVhZGVyOnJlYWRlcjEyMw==" value as READER
    *
    * Here the Varchar part that is after Basic is the base64 encoding of the passwords for ADMIN and READER
    * */

    @Autowired
    UserRepository userRepository;
    CalculateDistance calculateDistance = new CalculateDistance();

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if ("admin".equals(username)) {
            return new User("admin", "{noop}admin123", AuthorityUtils.createAuthorityList("ADMIN"));
        } else if ("reader".equals(username)) {
            return new User("reader", "{noop}reader123", AuthorityUtils.createAuthorityList("READER"));
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }


    // Create User Method - receiving data from Controller layer, data processing and storing happens here
    public UserLocation createUser(UserLocation userLocation){
        UserLocation savedUserLocation = userRepository.save(userLocation);
        return savedUserLocation;
    }

    // Update User Method - receives the id of the user and the updated data for the user
    // Check whether user with Id is present or not:
    //      -   if present then update
    //      -   if not return UserLocation object with all null values
    public UserLocation updateUser(Long id, UserLocation userLocation){
        Optional<UserLocation> opUserLocation = userRepository.findById(id);
        if(opUserLocation.isEmpty()){
            return new UserLocation();
        }

        UserLocation existingUserLocation = opUserLocation.get();
        existingUserLocation.setName(userLocation.getName());
        existingUserLocation.setLongitude(userLocation.getLongitude());
        existingUserLocation.setLatitude(userLocation.getLatitude());
        UserLocation savedUserLocation = userRepository.save(existingUserLocation);

        return savedUserLocation;
    }

     /*
     * Get Nearest Users Method
     * Find all the users,
     * Calculate the distance from point (0,0) and sort them according to their distance
     * Take the sublist of size minimum of count or userLocations list size
     * Return the sublist
      */
    public List<UserLocation> getNearestUsers(Integer count){
        List<UserLocation> userLocations = userRepository.findAll();
        userLocations.sort(Comparator.comparingDouble(u -> calculateDistance.getDistance(u.getLatitude(), u.getLongitude(), 0.0, 0.0)));
        List<UserLocation> nearestUserLocations = userLocations.subList(0, Math.min(count, userLocations.size()));
        return nearestUserLocations;
    }
}
