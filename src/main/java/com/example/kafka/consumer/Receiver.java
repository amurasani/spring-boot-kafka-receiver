package com.example.kafka.consumer;

import java.util.concurrent.CountDownLatch;

import com.google.protobuf.InvalidProtocolBufferException;
import com.protobuf.order.OrderProto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;

public class Receiver {

    private static final Logger LOGGER =
        LoggerFactory.getLogger(Receiver.class);

    @KafkaListener(topics = "${kafka.topic.order}")
    public void receive(byte[] data) {
        try {
            OrderProto.Order payload = OrderProto.Order.parseFrom(data);
            LOGGER.info("received payload='{}'", payload);
        } catch (final InvalidProtocolBufferException e) {
            LOGGER.error("Message: "+ new String(data));
            LOGGER.error("Received unparseable message", e);
            throw new RuntimeException("Received unparseable message " + e.getMessage(), e);
        }
    }
}
