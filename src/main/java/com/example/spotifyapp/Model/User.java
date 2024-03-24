package com.example.spotifyapp.Model;

/*
 * create the following fields
 * emailId,password,userName,mobile
 * use Lombok to generate no-args constructor,All Argument constructor, getters, setters, and toString() method.
 * Use @Document annotation to specify the collection name in mysql.
 */

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user")
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {

    @Id
    private String emailId;
    private String password;
    private String userName;
    private String mobile;


}
