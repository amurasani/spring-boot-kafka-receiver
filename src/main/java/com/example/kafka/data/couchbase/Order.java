package com.example.kafka.data.couchbase;

import com.couchbase.client.java.repository.annotation.Field;
import com.couchbase.client.java.repository.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Document;

@Document
public class Order {

    @Id
    private String id;

    @Field
    private String orderName;

    @Field
    private String orderAddress;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public String getOrderAddress() {
        return orderAddress;
    }

    public void setOrderAddress(String orderAddress) {
        this.orderAddress = orderAddress;
    }

    @Override
    public String toString() {
        return "Order{" +
            "id='" + id + '\'' +
            ", orderName='" + orderName + '\'' +
            ", orderAddress='" + orderAddress + '\'' +
            '}';
    }
}
