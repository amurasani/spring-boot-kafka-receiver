package com.example.kafka.data.couchbase;


import org.springframework.data.couchbase.core.query.ViewIndexed;
import org.springframework.data.couchbase.repository.CouchbaseRepository;

@ViewIndexed(designDoc = "schedule", viewName = "all")
public interface ScheduleRepository extends CouchbaseRepository<Schedule, String> {

}
