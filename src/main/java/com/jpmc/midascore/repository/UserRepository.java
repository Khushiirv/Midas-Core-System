package com.jpmc.midascore.repository;

import com.jpmc.midascore.data.model.CustomerRecord;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<CustomerRecord, Long> {
    CustomerRecord findById(long id);
}
