package com.example.kafka.data.couchbase;

import org.springframework.data.couchbase.core.query.ViewIndexed;
import org.springframework.data.couchbase.repository.CouchbaseRepository;

@ViewIndexed(designDoc = "order", viewName = "all")
public interface OrderRepository extends CouchbaseRepository<Order, String> {

}
