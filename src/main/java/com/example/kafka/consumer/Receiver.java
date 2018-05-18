package com.example.kafka.consumer;

import com.google.protobuf.InvalidProtocolBufferException;
import com.protobuf.order.OrderProto;
import com.protobuf.schedule.ScheduleProto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;

public class Receiver {

    private static final Logger LOGGER =
        LoggerFactory.getLogger(Receiver.class);

    @KafkaListener(topics = "${kafka.topic.order}")
    public void receiveOrder(byte[] data) {
        try {
            OrderProto.Order payload = OrderProto.Order.parseFrom(data);
            LOGGER.info("received order payload='{}'", payload);
        } catch (final InvalidProtocolBufferException e) {
            LOGGER.error("Message: "+ new String(data));
            LOGGER.error("Received unparseable message", e);
            throw new RuntimeException("Received unparseable message " + e.getMessage(), e);
        }
    }

    @KafkaListener(topics = "${kafka.topic.schedule}")
    public void receiveSchedule(byte[] data) {
        try {
            ScheduleProto.Schedule payload = ScheduleProto.Schedule.parseFrom(data);
            LOGGER.info("received schedule payload='{}'", payload);
        } catch (final InvalidProtocolBufferException e) {
            LOGGER.error("Message: "+ new String(data));
            LOGGER.error("Received unparseable message", e);
            throw new RuntimeException("Received unparseable message " + e.getMessage(), e);
        }
    }
}
