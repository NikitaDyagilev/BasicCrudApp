package com.crudapp.controllers;

import com.crudapp.model.User;
import com.crudapp.repository.JpaUserRepository;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@Setter
public class AccountPageController {

    @Autowired
    @Qualifier("jpaUserRepositoryImpl")
    private JpaUserRepository jpaRepo;


    @RequestMapping(value = "/accountPage", method = GET)
    public String accountPage(){
        return "accountPage";
    }

    @RequestMapping(value="/fetchUserData", method= {GET, POST})
    @ResponseBody
    public User fetchUserData(@RequestParam(value = "username") String username){
        Optional<User> user = jpaRepo.findByUsername(username);
        if(user.isPresent()){
            User resultUser = user.get();
            resultUser.setPassword(null);
        return resultUser;
        } else {
            System.out.println("Could not Find a user with that username");
            return null;
        }
    }
}
