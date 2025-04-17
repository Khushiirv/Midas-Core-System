package com.jpmc.midascore;

import com.jpmc.midascore.utility.StorageHandler;
import com.jpmc.midascore.data.model.CustomerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserPopulator {
    @Autowired
    private FileLoader fileLoader;

    @Autowired
    private StorageHandler storageHandler;

    public void populate() {
        String[] userLines = fileLoader.loadStrings("/test_data/lkjhgfdsa.hjkl");
        for (String userLine : userLines) {
            String[] userData = userLine.split(", ");
            CustomerRecord user = new CustomerRecord(userData[0], Float.parseFloat(userData[1]));
            storageHandler.save(user);
        }
    }
}
