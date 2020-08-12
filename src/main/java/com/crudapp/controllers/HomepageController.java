package com.crudapp.controllers;

import com.crudapp.model.User;
import com.crudapp.repository.JpaUserRepository;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;

@Controller
@Setter
public class HomepageController {

    @Autowired
    @Qualifier("jpaUserRepositoryImpl")
    private JpaUserRepository jpaRepo;

    @Autowired
    @Qualifier("passwordEncoder")
    private PasswordEncoder passwordEncoder;

    @GetMapping("/")
    public String getHomepage() {
        return "homepage";
    }

    @RequestMapping(value ="/signUp",method = RequestMethod.POST)
    public void signup(@RequestBody User user, HttpServletResponse response){
        System.out.println(user.toString());
        String pass = user.getPassword();
        user.setPassword(passwordEncoder.encode(pass));
        try {
            jpaRepo.save(user);
            response.setHeader("isSuccessful", "true");
        } catch(Exception e){
            System.out.println(e);
            response.setHeader("isSuccessful", "false");
        }
    }
}

