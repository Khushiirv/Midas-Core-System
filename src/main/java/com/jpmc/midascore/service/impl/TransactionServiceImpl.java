package com.jpmc.midascore.service.impl;

import com.jpmc.midascore.entity.UserRecord;
import com.jpmc.midascore.foundation.Transaction;
import com.jpmc.midascore.payload.TransactionResponse;
import com.jpmc.midascore.repository.UserRepository;
import com.jpmc.midascore.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    @Override
    public TransactionResponse transfer(Long senderID, Long receiverID, Float amount) {

        UserRecord sender = null;
        UserRecord recipient = null;
        Optional<UserRecord> optSender = userRepository.findById(senderID);
        Optional<UserRecord> optRecipient = userRepository.findById(receiverID);

        if(optSender.isEmpty() && optRecipient.isEmpty()) {
            return new TransactionResponse(null, true);
        }
        else{
            sender = optSender.get();
            recipient = optRecipient.get();
        }

        System.out.println(sender);
        System.out.println(recipient);

        float newSenderBalance = withdraw(sender, amount);

        if((amount <= 0 || newSenderBalance < 0)) {
            return new TransactionResponse(null, true);
        }
        else {
            sender.setBalance(newSenderBalance);
            float newReceiverBalance = deposit(recipient, amount);
            recipient.setBalance(newReceiverBalance);
            userRepository.save(sender);
            userRepository.save(recipient);
            return new TransactionResponse(new Transaction(senderID, receiverID, amount), false);
        }
    }

    @Override
    public Float deposit(UserRecord receiver, Float amount) {
        return receiver.getBalance() + amount;
    }

    @Override
    public Float withdraw(UserRecord sender, Float amount) {
        return sender.getBalance() - amount;
    }
}