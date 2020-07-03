package com.crudapp.controllers;

import com.crudapp.model.User;
import com.crudapp.repository.JpaUserRepository;
import com.crudapp.security.auth.SecureUser;
import com.mysql.cj.x.protobuf.Mysqlx;
import lombok.Setter;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Controller
@Setter
@ComponentScan(basePackages = "com.crudapp.security")
public class HomepageController {

    @Autowired
    @Qualifier("jpaUserRepositoryImpl")
    private JpaUserRepository jpaRepo;

    @Autowired
    @Qualifier("passwordEncoder")
    private PasswordEncoder passwordEncoder;

    @GetMapping
    public String getHomepage() {
        return "homepage";
    }

    @RequestMapping(value ="/signUp",method = RequestMethod.POST)
    public String signup(@RequestBody User user){
        String pass = user.getPassword();
        user.setPassword(passwordEncoder.encode(pass));
        jpaRepo.save(user);
        return "homepage";
    }

    @RequestMapping(value="/logIn",method = {RequestMethod.POST,RequestMethod.GET})
    public void login(){
    }

    @RequestMapping(value="/logOut",method={RequestMethod.POST,RequestMethod.GET})
    public void logout(){

    }

}
