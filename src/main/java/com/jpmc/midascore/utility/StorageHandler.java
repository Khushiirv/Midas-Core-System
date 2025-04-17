package com.jpmc.midascore.utility;

import com.jpmc.midascore.data.model.CustomerRecord;
import com.jpmc.midascore.repository.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class StorageHandler {
    private final UserRepository userRepository;

    public StorageHandler(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void save(CustomerRecord userRecord) {
        userRepository.save(userRecord);
    }

}
