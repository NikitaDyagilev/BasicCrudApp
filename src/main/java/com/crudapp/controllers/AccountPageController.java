package com.crudapp.controllers;

import com.crudapp.model.User;
import com.crudapp.repository.JpaUserRepository;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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

    @RequestMapping(value="/changeAccSettings",method = POST)
    public void changeBio(@RequestBody User user){
        jpaRepo.save(user);
    }
}
