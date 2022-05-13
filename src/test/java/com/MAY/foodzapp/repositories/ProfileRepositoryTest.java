package com.MAY.foodzapp.repositories;

import com.MAY.foodzapp.entities.Profile;
import com.MAY.foodzapp.utils.UserRoles;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProfileRepositoryTest {


    // TODO: 09-05-2022 Profile Unit Testing : Success
    @Autowired
    private ProfileRepository profileRepository;

    @Test
    public void AddUserProfilesTest(){
        Profile profile1 = Profile.builder()
                .firstName("Yash")
                .lastName("Patil")
                .email("xyz@ryk.com")
                .password("122334")
                .role(UserRoles.USER)
                .build();

        Profile profile2 = Profile.builder()
                .firstName("Madhura")
                .lastName("Ashture")
                .email("pqr@ryk.com")
                .password("122334")
                .role(UserRoles.USER)
                .build();

        Profile profile3 = Profile.builder()
                .firstName("Aryaa")
                .lastName("Walke")
                .email("xyz@ryk.com")
                .password("122334")
                .role(UserRoles.USER)
                .build();

        profileRepository.save(profile1);
        profileRepository.save(profile2);
        profileRepository.save(profile3);

    }


    @Test
    public void printAllProfilesTest(){
        List<Profile> profileList = profileRepository.findAll();
        System.out.println(profileList);
    }



    @Test
    public void printProfileByFirstNameTest(){
//        List<Profile> profileList = profileRepository.findByFirstName("yash");
        List<Profile> profileList = profileRepository.findByFirstNameIgnoreCase("yash");
        System.out.println(profileList);
    }

//    @Test
    public void deleteAllUserTest(){
        profileRepository.deleteAll();
    }
}