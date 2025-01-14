package com.example.santa.board.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardVO {
    private int boardId;
    private String boardType;
    private int authorId;
    private String title;
    private String content; //리스트일 때는 content빼고
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}


