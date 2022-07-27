package com.peaksoft.controller;

import com.peaksoft.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class HelloController {

    private UserDao userDao;

    @Autowired
    public HelloController(UserDao userDao) {
        this.userDao = userDao;
    }

    @GetMapping
    public String hello(){
        return "hello";
    }

    @GetMapping("/profile/{username}")
    public String getProfile(@PathVariable("username") String username, Model model){
        model.addAttribute("user", userDao.findByUserName(username));
        return "profile";
    }

    @RequestMapping("/login")
    public String login(){
        return "login";
    }
}
