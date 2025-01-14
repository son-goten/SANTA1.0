package com.example.santa.reply.mapper;

import com.example.santa.reply.vo.ReplyVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReplyMapper {

    // 특정 게시글의 모든 댓글 조회
    List<ReplyVO> selectReplyByBoardId(int boardId);

    //댓글 추가
    int insertReply(ReplyVO replyVO);

    //댓글 수정
    int updateReply(ReplyVO replyVO);

    //댓글 삭제
    int deleteReply(int replyId);


}
