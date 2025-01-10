package com.example.santa.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.example.santa.log.vo.MongoLog;
import org.springframework.stereotype.Repository;

@Repository
public interface LogRepository extends MongoRepository<MongoLog, String> {
}
