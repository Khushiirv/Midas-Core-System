package com.jpmc.midascore.controller;

import com.jpmc.midascore.data.entity.UserRecord;
import com.jpmc.midascore.foundation.Balance;
import com.jpmc.midascore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BalanceController {

    @Autowired
    private UserService userService;

    @GetMapping("/balance")
    ResponseEntity<Balance> getBalance(Long userId){
        UserRecord user = userService.getUserByID(userId);
        if(user == null)
            return ResponseEntity.ok(new Balance(0));
        else
            return ResponseEntity.ok(new Balance(user.getBalance()));
    }
}