package com.jpmc.midascore.service;

import com.jpmc.midascore.data.entity.UserRecord;

public interface UserService {
    UserRecord getUserByID(Long userID);
}