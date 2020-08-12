package com.crudapp.controllers;

import com.crudapp.model.User;
import com.crudapp.repository.JpaUserRepository;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Setter
@Controller
public class AccountSettingsController {

    @Autowired
    @Qualifier("jpaUserRepositoryImpl")
    private JpaUserRepository jpaRepo;

    @Autowired
    @Qualifier("passwordEncoder")
    private PasswordEncoder passwordEncoder;

    @RequestMapping(value = "/accountSettings", method = RequestMethod.GET)
    public String getAccountSettingsPage() {
        return "accountSettings";
    }

    @RequestMapping(value = "/setNewUserBasicInfo", method = {RequestMethod.GET, RequestMethod.POST})
    public void setNewUserBasicInfo(@RequestParam(value = "oldUsername") String oldUsername,
                                    @RequestParam(value = "username") String username,
                                    @RequestParam(value = "firstName") String firstName,
                                    @RequestParam(value = "lastName") String lastName,
                                    HttpServletResponse response) {

        Optional<User> oldUser = jpaRepo.findByUsername(oldUsername);
        if (oldUser.isEmpty()) {
            response.setHeader("isSuccessful", "false");
            return;
        }
        User user = oldUser.get();

        if (username == "" || username == null) {
        } else {
            user.setUsername(username);
        }
        if (firstName == "" || firstName == null) {
        } else {
            user.setFirst_name(firstName);
        }
        if(lastName == "" || lastName == null){
        } else {
            user.setLast_name(lastName);
        }
        jpaRepo.flush();

        response.setHeader("isSuccessful", "true");
    }

    @RequestMapping(value = "/setNewUserBio", method = RequestMethod.POST)
    public void setNewUserBio(@RequestParam(value = "username") String username,
                              @RequestParam(value = "bio") String bio,
                              HttpServletResponse response) {
        Optional<User> oldUser = jpaRepo.findByUsername(username);
        if (oldUser.isEmpty()) {
            return;
        }
        User newUser = oldUser.get();
        newUser.setBio(bio);
        jpaRepo.flush();

        response.setHeader("isSuccessful", "true");
    }

    @RequestMapping(value = "/setNewUserPassword", method = RequestMethod.POST)
    public void setNewUserPassword(@RequestParam(value = "username") String username,
                                   @RequestBody PasswordContainer password,
                                   HttpServletResponse response) {
        Optional<User> oldUser = jpaRepo.findByUsername(username);
        if (oldUser.isEmpty()) {
            response.setHeader("isSuccessful", "false");
            return;
        }

        User newUser = oldUser.get();
        String newPassword = passwordEncoder.encode(password.getPassword());

        newUser.setPassword(newPassword);

        jpaRepo.flush();
        response.setHeader("isSuccessful", "true");
    }

    @RequestMapping(value = "/verifyOldPassword", method = RequestMethod.POST)
    public void verifyOldPassword(@RequestParam(value = "username") String username,
                                  @RequestBody PasswordContainer oldPassword,
                                  HttpServletResponse response) {
        Optional<User> user = jpaRepo.findByUsername(username);
        if (user.isEmpty()) {
            return;
        }
        System.out.println(username);
        System.out.println(oldPassword.getPassword());
        if (passwordEncoder.matches(oldPassword.getPassword(),
                user.get().getPassword())) {
            System.out.println("Passwords MATCH!");
            response.setHeader("isPasswordsMatching", "true");
            return;
        }
        System.out.println("Passwords do NOT match!");
        response.setHeader("isPasswordsMatching", "false");
    }

    @RequestMapping(value = "/accountDelete", method = RequestMethod.DELETE)
    public void deleteAccount(@RequestParam(value = "username") String username,
                                HttpServletResponse response) {
        Optional<User> user = jpaRepo.findByUsername(username);
        if (user.isEmpty()) {
            response.setHeader("isAccountDeleted", "false");
        }

        jpaRepo.delete(user.get());
        response.setHeader("isAccountDeleted", "true");
    }

}
