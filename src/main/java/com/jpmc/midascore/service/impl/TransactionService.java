package com.jpmc.midascore.service;

import com.jpmc.midascore.data.entity.UserRecord;
import com.jpmc.midascore.payload.TransactionResponse;

public interface TransactionService {
    TransactionResponse transfer(Long senderID, Long receiverID, Float amount);
    Float deposit(UserRecord user, Float amount);
    Float withdraw(UserRecord user, Float amount);
}