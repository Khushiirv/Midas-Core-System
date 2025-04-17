package com.jpmc.midascore.controller;

import com.jpmc.midascore.data.model.CustomerRecord;
import com.jpmc.midascore.foundation.Balance;
import com.jpmc.midascore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class messagecontroller {

    @Autowired
    private UserService userService;

    @GetMapping("/balance")
    ResponseEntity<Balance> getBalance(Long userId){
        CustomerRecord user = userService.getUserByID(userId);
        if(user == null)
            return ResponseEntity.ok(new Balance(0));
        else
            return ResponseEntity.ok(new Balance(user.getBalance()));
    }
}