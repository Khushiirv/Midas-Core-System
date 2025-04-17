package com.jpmc.midascore.service;

import com.jpmc.midascore.data.model.CustomerRecord;
import com.jpmc.midascore.payload.TransactionResponse;

public interface TransactionService {
    TransactionResponse transfer(Long senderID, Long receiverID, Float amount);
    Float deposit(CustomerRecord user, Float amount);
    Float withdraw(CustomerRecord user, Float amount);
}