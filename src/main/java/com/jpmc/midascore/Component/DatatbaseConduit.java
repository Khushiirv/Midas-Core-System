package com.jpmc.midascore.Component;

import com.jpmc.midascore.data.entity.UserRecord;
import com.jpmc.midascore.repository.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class DatatbaseConduit {
    private final UserRepository userRepository;

    public DatatbaseConduit(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void save(UserRecord userRecord) {
        userRepository.save(userRecord);
    }

}
