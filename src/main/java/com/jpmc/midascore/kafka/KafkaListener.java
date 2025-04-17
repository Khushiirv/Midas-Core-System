package com.jpmc.midascore.kafka;

import com.jpmc.midascore.utility.StorageHandler;
import com.jpmc.midascore.data.model.CustomerRecord;
import com.jpmc.midascore.foundation.Incentive;
import com.jpmc.midascore.foundation.Transaction;
import com.jpmc.midascore.payload.TransactionResponse;
import com.jpmc.midascore.service.RestTemplateService;
import com.jpmc.midascore.service.TransactionService;
import com.jpmc.midascore.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class KafkaListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaListener.class);

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private UserService userService;

    @Autowired
    private RestTemplateService restTemplateService;

    @Autowired
    private StorageHandler storageHandler;

    @org.springframework.kafka.annotation.KafkaListener(topics = "transactions")
    public TransactionResponse consumeTransaction(Transaction transaction) {
        try {
            LOGGER.info("Received transaction: " + transaction);

            // Step 1: Process the transaction and transfer funds
            TransactionResponse transactionResponse = transactionService.transfer(
                    transaction.getSenderId(),
                    transaction.getRecipientId(),
                    transaction.getAmount());

            // Step 2: If there is an error in the transaction, handle it and return failure
            if (transactionResponse.getError()) {
                LOGGER.error("Error processing transaction: " + transaction);
                // Using the correct constructor: TransactionResponse(Transaction, Boolean)
                return new TransactionResponse(transaction, true);  // Handle error case
            }

            // Step 3: Fetch sender and recipient from the database
            CustomerRecord sender = userService.getUserByID(transaction.getSenderId());
            CustomerRecord recipient = userService.getUserByID(transaction.getRecipientId());

            if (sender == null || recipient == null) {
                LOGGER.error("Sender or recipient not found. Sender ID: " + transaction.getSenderId() + ", Recipient ID: " + transaction.getRecipientId());
                // Using the correct constructor: TransactionResponse(Transaction, Boolean)
                return new TransactionResponse(transaction, true);  // Handle missing users
            }

            // Step 4: Fetch incentives if applicable
            Incentive incentive = null;
            try {
                incentive = restTemplateService.postTransaction(transactionResponse.getTransaction());
            } catch (Exception e) {
                LOGGER.error("Failed to fetch incentive for transaction: " + transaction, e);
                return new TransactionResponse(transaction, true);  // Handle incentive fetch failure
            }

            // Step 5: Update recipient balance and save the records
            if (incentive != null && incentive.getAmount() > 0) {
                transaction.setIncentive(incentive);
                float newRecipientBalance = recipient.getBalance() + incentive.getAmount();
                recipient.setBalance(newRecipientBalance);

                // Wrap in a try-catch for database operations
                try {
                    storageHandler.save(sender);
                    storageHandler.save(recipient);
                } catch (Exception e) {
                    LOGGER.error("Database save failed for sender: " + sender.getId() + " or recipient: " + recipient.getId(), e);
                    return new TransactionResponse(transaction, true);  // Handle database failure
                }
            }

            return transactionResponse;

        } catch (Exception e) {
            LOGGER.error("Error processing transaction: " + transaction, e);
            return new TransactionResponse(transaction, true);  // Handle general failure
        }
    }
}
