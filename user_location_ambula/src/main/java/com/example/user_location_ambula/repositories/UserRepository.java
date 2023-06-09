package com.example.user_location_ambula.repositories;

import com.example.user_location_ambula.entities.UserLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserLocation, Long> {
}
