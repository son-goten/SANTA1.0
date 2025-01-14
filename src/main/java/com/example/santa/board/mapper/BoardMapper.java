package com.example.santa.board.mapper;

import com.example.santa.board.vo.BoardVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BoardMapper {


    int insertBoard(BoardVO boardVO);
    List<BoardVO> selectBoardAll();
    BoardVO selectById(int boardId);
    int updateBoard(BoardVO boardVO);
    int deleteBoard(int boardId);


}
