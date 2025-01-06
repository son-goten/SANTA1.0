package com.example.santa.log.vo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;

@Document(collection = "application_logs")
public class MongoLog {

    @Id
    private String id; // MongoDB의 고유 ID

    private Long userId; // 사용자 ID (null 가능)
    private String role; // 역할: "USER", "ADMIN", "SYSTEM"
    private String action; // 작업 이름 (예: "CREATE_ORDER")
    private String details; // 작업 세부사항
    private LocalDateTime timestamp; // 작업 시간

    public MongoLog(Long userId, String role, String action, String details) {
        this.userId = userId;
        this.role = role;
        this.action = action;
        this.details = details;
        this.timestamp = LocalDateTime.now();
    }

    // Getters and Setters
}
