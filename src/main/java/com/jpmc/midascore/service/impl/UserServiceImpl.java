package com.jpmc.midascore.service.impl;

import com.jpmc.midascore.data.entity.UserRecord;
import com.jpmc.midascore.repository.UserRepository;
import com.jpmc.midascore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserRecord getUserByID(Long userID) {
        Optional<UserRecord> optionalUser = userRepository.findById(userID);

        return optionalUser.orElse(null);
    }
}