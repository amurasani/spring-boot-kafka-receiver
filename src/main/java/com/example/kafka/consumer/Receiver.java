package com.example.kafka.consumer;

import com.example.kafka.data.couchbase.Order;
import com.example.kafka.data.couchbase.OrderRepository;
import com.example.kafka.data.couchbase.Schedule;
import com.example.kafka.data.couchbase.ScheduleRepository;
import com.google.protobuf.InvalidProtocolBufferException;
import com.protobuf.order.OrderProto;
import com.protobuf.schedule.ScheduleProto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;

import java.util.List;

public class Receiver {

    private static final Logger LOGGER =
        LoggerFactory.getLogger(Receiver.class);

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ScheduleRepository scheduleRepository;

    @KafkaListener(topics = "${kafka.topic.order}")
    public void receiveOrder(byte[] data) {
        try {
            OrderProto.Order payload = OrderProto.Order.parseFrom(data);
            LOGGER.info("received order payload='{}'", payload);
            Order persistent = toOrder(payload);
            LOGGER.info("Saving document '{}' to couchbase.", persistent);
            orderRepository.save(persistent);
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
            Schedule persistent = toSchedule(payload);
            LOGGER.info("Saving document '{}' to couchbase.", persistent);
            scheduleRepository.save(persistent);
        } catch (final InvalidProtocolBufferException e) {
            LOGGER.error("Message: "+ new String(data));
            LOGGER.error("Received unparseable message", e);
            throw new RuntimeException("Received unparseable message " + e.getMessage(), e);
        }
    }

    private Order toOrder(OrderProto.Order orderProto) {
        Order order = new Order();
        order.setId("" + orderProto.getOrderId());
        order.setOrderName(orderProto.getName());
        order.setOrderAddress(orderProto.getOrderAddress());
        return order;
    }

    private Schedule toSchedule(ScheduleProto.Schedule scheduleProto) {
        Schedule schedule = new Schedule();
        schedule.setId("" + scheduleProto.getId());
        schedule.setFlightNumber(scheduleProto.getFlightNumber());
        schedule.setHosted(scheduleProto.getHosted());
        schedule.setVersion(scheduleProto.getVersion());
        return schedule;
    }
}
