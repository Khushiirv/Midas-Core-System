package com.jpmc.midascore.kafka;

import com.jpmc.midascore.data.model.CustomerRecord;
import com.jpmc.midascore.foundation.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class TransactionProducer {
    private static final Logger LOGGER = LoggerFactory.getLogger(TransactionProducer.class);

    private KafkaTemplate<String, CustomerRecord> kafkaTemplate;

    public TransactionProducer(KafkaTemplate<String, CustomerRecord> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendUser(CustomerRecord user){
        LOGGER.info(String.format("User message sent: %s", user.toString()));

        Message<CustomerRecord> userMessage = MessageBuilder
                .withPayload(user)
                .setHeader(KafkaHeaders.TOPIC, "users")
                .build();

        kafkaTemplate.send(userMessage);
    }

    public void sendTransaction(Transaction transaction){
        LOGGER.info(String.format("Transaction message sent: %s", transaction.toString()));

        Message<Transaction> transactionMessage = MessageBuilder
                .withPayload(transaction)
                .setHeader(KafkaHeaders.TOPIC, "transactions")
                .build();

        kafkaTemplate.send(transactionMessage);
    }
}