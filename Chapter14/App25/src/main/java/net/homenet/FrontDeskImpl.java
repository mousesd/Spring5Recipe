package net.homenet;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.core.KafkaOperations;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

public class FrontDeskImpl implements FrontDesk {
    private final KafkaOperations<Integer, String> kafkaOperations;

    public FrontDeskImpl(KafkaOperations<Integer, String> kafkaOperations) {
        this.kafkaOperations = kafkaOperations;
    }

    @Override
    public void sendMail(Mail mail) {
        ListenableFuture<SendResult<Integer, String>> future = kafkaOperations.send("mails", toJsonString(mail));
        future.addCallback(new ListenableFutureCallback<SendResult<Integer, String>>() {
            @Override
            public void onFailure(Throwable ex) {
                System.out.println("Result (fail): " + ex.getMessage());
            }

            @Override
            public void onSuccess(SendResult<Integer, String> result) {
                System.out.println("Result (success): " + result.getRecordMetadata());
            }
        });
    }

    private String toJsonString(Mail mail) {
        try {
            return new ObjectMapper().writeValueAsString(mail);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
