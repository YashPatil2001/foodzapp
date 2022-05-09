package com.MAY.foodzapp.entities;

import com.MAY.foodzapp.utils.UserRoles;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "profile")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer","handler"})
public class Profile {


    @Id
    @SequenceGenerator(
            name = "profile_sequence",
            sequenceName = "profile_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "profile_sequence"
    )
    private Long profileId;

    private String email;

    private String firstName;
    private String lastName;
    private String password;
    private UserRoles role;





}
