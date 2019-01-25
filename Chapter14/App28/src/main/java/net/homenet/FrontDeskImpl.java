package net.homenet;

import org.springframework.kafka.core.KafkaOperations;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

public class FrontDeskImpl implements FrontDesk {
    private final KafkaOperations<Integer, Object> kafkaOperations;

    public FrontDeskImpl(KafkaOperations<Integer, Object> kafkaOperations) {
        this.kafkaOperations = kafkaOperations;
    }

    @Override
    public void sendMail(Mail mail) {
        ListenableFuture<SendResult<Integer, Object>> future = kafkaOperations.send("mails", mail);
        future.addCallback(new ListenableFutureCallback<SendResult<Integer, Object>>() {
            @Override
            public void onFailure(Throwable ex) {
                System.out.println("Result (fail): " + ex.getMessage());
            }

            @Override
            public void onSuccess(SendResult<Integer, Object> result) {
                System.out.println("Result (success): " + result.getRecordMetadata());
            }
        });
    }
}
