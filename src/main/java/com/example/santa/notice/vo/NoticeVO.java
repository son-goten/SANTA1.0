package com.example.santa.notice.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NoticeVO {
//    private int authorId;
    private int noticeId;
    private String title;
    private String content;
    private int categoryId;
}
