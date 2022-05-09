package com.MAY.foodzapp.controllers;


import com.MAY.foodzapp.entities.Profile;
import com.MAY.foodzapp.models.LoginUserModel;
import com.MAY.foodzapp.services.profile.ProfileService;
import com.MAY.foodzapp.utils.ResultFlags;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private ProfileService profileService;


    @GetMapping("/test")
    private String appInit(){
        return "<h1> Welcome to Auth route of FoodsApp</h1>";
    }

    @PostMapping("/signup")
    private ResultFlags singUpUser(@RequestBody Profile profile){
        return profileService.createUser(profile);
    }

    @GetMapping("/validation/{code}")
    private ResultFlags validateUser(@PathVariable("code") String code){
        return profileService.validateCode(code);
    }

    @PostMapping("/login")
    private ResultFlags loginUser(@RequestBody LoginUserModel user){
        System.out.println("user : " + user);
        return  profileService.loginUser(user);
    }
}
