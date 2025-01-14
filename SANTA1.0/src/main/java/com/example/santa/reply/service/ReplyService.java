package com.example.santa.reply.service;

import com.example.santa.reply.mapper.ReplyMapper;
import com.example.santa.reply.vo.ReplyVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReplyService {
    // 특정 게시글의 모든 댓글 조회

    private final ReplyMapper replyMapper;

    public List<ReplyVO> selectReplyByBoardId(int boardId){
        return replyMapper.selectReplyByBoardId(boardId);
    }

    // 댓글 추가
    public int insertReply(ReplyVO replyVO){
        return replyMapper.insertReply(replyVO);
    }

    // 댓글 수정
    public int updateReply(ReplyVO replyVO){
        return replyMapper.updateReply(replyVO);
    }

    // 댓글 삭제
    public int deleteReply(int replyId) {
        return replyMapper.deleteReply(replyId);

    }



}