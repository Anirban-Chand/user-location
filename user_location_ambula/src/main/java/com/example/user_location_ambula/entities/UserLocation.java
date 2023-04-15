package com.example.user_location_ambula.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "user_location")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserLocation {

    /*
    * Main Entity that stores the name of the user, their latitude and longitude
    * Using HSQL in-memory database
    * Name of the table - "user_location"
    * Lombok dependency is used for generating getters, setters, and constructors
    */


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)     // Generates the sequence of IDs for the users
    private Long id;    // UserID - we set it as primary key of the user_location table

    @NotBlank           // Name field cannot contain null or empty string
    private String name;

    @NotNull            // Cannot have null values
    private Double latitude;

    @NotNull
    private Double longitude;
}
