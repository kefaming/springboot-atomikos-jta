package com.atomikos.jta.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atomikos.jta.model.User;
import com.atomikos.jta.service.UserService;

/**
 * Created by hyson on 2017/3/10.
 */
@RestController
public class MessageController {
    @Autowired
    UserService userService;

//    @GetMapping("/add")
//    public String add(User user){
//        try {
//            userService.add(user);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return "add";
//    }
//
//    @GetMapping("/del")
//    public String del(Long id){
//        try {
//            userService.del(id);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return "del";
//    }
    
    
    
    @GetMapping("/sendSuggestData")
    public String sendSuggestData(){
        try {
            userService.sendSuggestData();
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
        return "success";
    }
    
    @GetMapping("/log")
    public String log(){
        try {
            userService.sendSuggestData();
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
        return "success";
    }
    
    
}  