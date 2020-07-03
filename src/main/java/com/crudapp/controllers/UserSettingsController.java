package com.crudapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserSettingsController {

    @RequestMapping(value = "/accountSettings", method = RequestMethod.GET)
    public String getAccountSettingsPage(){
        return "accountSettings";
    }

}
