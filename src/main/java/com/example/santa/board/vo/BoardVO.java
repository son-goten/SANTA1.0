package com.example.santa.Board.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data //get/set, toString코드 자동 생성
@NoArgsConstructor //기본 생성자 자동 생성
@AllArgsConstructor //모든 멤버변수 생성자 자동 생성
@Builder
public class BoardVO {
    private int board_id;
    private String board_type;
    private int author_id;
    private String title;
    private String content;
    private int parent_id;
    private String created_at;
    private String updated_at;

}



