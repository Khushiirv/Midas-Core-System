package com.jpmc.midascore.service;

import com.jpmc.midascore.entity.UserRecord;

public interface UserService {
    UserRecord getUserByID(Long userID);
}