package com.jpmc.midascore;

import com.jpmc.midascore.kafka.KafkaProducer;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.annotation.DirtiesContext;

@SpringBootTest
@DirtiesContext
@EmbeddedKafka(partitions = 1, brokerProperties = {
        "listeners=PLAINTEXT://localhost:9092", "port=9092"
})
class TaskTwoTests {

    static final Logger logger = LoggerFactory.getLogger(TaskTwoTests.class);

    @Autowired
    private KafkaProducer kafkaProducer;

    @Autowired
    private FileLoader fileLoader;

    @Test
    void task_two_verifier() throws InterruptedException {
        // Load test data from file
        String[] transactionLines = fileLoader.loadStrings("/test_data/poiuytrewq.uiop");

        // Send each line to Kafka
        for (String transactionLine : transactionLines) {
            kafkaProducer.send(transactionLine);
            logger.info("Sent to Kafka: {}", transactionLine);
        }

        // Wait for consumer to pick up messages (adjust if needed)
        Thread.sleep(5000);

        logger.info("----------------------------------------------------------");
        logger.info("Test messages sent. Check your consumer logs for activity.");
        logger.info("Test completed.");
    }
}
