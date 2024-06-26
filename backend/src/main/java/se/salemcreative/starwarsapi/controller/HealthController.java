package se.salemcreative.starwarsapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import se.salemcreative.starwarsapi.model.ProfileData;

@RestController
public class HealthController {

    @Autowired
    Environment env;

    @RequestMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public String index() {
        return "Greetings from the Star Wars API!";
    }

    @GetMapping("/profile")
    public ProfileData getProfileData() {
        return new ProfileData(env);
    }

}
