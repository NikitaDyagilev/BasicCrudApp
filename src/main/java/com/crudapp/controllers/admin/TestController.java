package com.crudapp.controllers.admin;

import com.crudapp.dao.UserDao;
import com.crudapp.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class TestController {

    @Autowired
    @Qualifier("accountDaoImpl")
    private UserDao accountDao;

    public void setAccountDao(UserDao userDao){
        this.accountDao = accountDao;
    }

    @RequestMapping(value = "/getAll",method = RequestMethod.GET)
    public String getSigners(){
        String message = "";

        List<User> users = accountDao.getAll();
        for (User a : users) {
            message += a.toString() + ", ";
        }

        return message;
    }

    @RequestMapping(value ="/sign", method= RequestMethod.POST)
    public String signUp(@RequestBody User user){
        accountDao.save(user);
        return user.getFirst_name() + ", You have Signed Up";
    }

    @RequestMapping(value ="/deleteUser/{id}", method = RequestMethod.DELETE)
    public String deleteUser(@PathVariable(name = "id") Long id){
        User user = accountDao.findById(id);
        accountDao.delete(user);
        return "Your account was successfuly deleted";
    }
}
