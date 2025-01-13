package com.example.santa.notice.service;

import com.example.santa.notice.mapper.NoticeMapper;
import com.example.santa.notice.vo.NoticeDTO;
import com.example.santa.notice.vo.NoticeVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NoticeService {

    final private NoticeMapper noticeMapper;

    public List<NoticeDTO> selectAllNotice() {
        return noticeMapper.selectAllNotice();
    }

    public int insertNotice(NoticeVO notice) {
        System.out.println("========insertNotice======" + notice);
        return noticeMapper.insertNotice(notice);
    }

    public int deleteNotice(NoticeDTO notice) {
        System.out.println("========noticeDelete======" + notice);
        return noticeMapper.deleteNotice(notice);
    }

    public NoticeDTO selectByNoticeId(int noticeId) {
        System.out.println("========NoticeServiceSelectByNoticeId=======" + noticeId);
        return noticeMapper.selectByNoticeId(noticeId);
    }
}
