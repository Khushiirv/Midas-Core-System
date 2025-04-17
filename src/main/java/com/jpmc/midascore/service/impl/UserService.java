package com.jpmc.midascore.service;

import com.jpmc.midascore.data.model.CustomerRecord;

public interface UserService {
    CustomerRecord getUserByID(Long userID);
}