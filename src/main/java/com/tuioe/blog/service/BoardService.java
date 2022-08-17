package com.tuioe.blog.service;

import com.tuioe.blog.Entity.Board;
import com.tuioe.blog.dto.BoardDTO;
import com.tuioe.blog.repositroy.BoardRepositroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class BoardService {

    @Autowired
    private BoardRepositroy boardRepositroy;

    //블로그의 모든 글을 반환
    public List<BoardDTO> findAllBoard(){
        List<Board> boards = boardRepositroy.findAll();
        List<BoardDTO> responseDTOS = new ArrayList<>();
        for(Board board: boards){ // 향상된 for문 사용 주로 배열에 사용
            responseDTOS.add(BoardDTO.create(board));
            //responseDTOS의 List 변수에 BoardDTO를 생성하여 저장
        }
        return responseDTOS;
    }
    //블로그에 작성된 특정 글을 반환
    @Transactional
    public BoardDTO findBoard(int id){
        boardRepositroy.updateHits(id);
        return BoardDTO.create(boardRepositroy.findById(id).get());
    }
    //블로그에 글 작성
    public void createBoard(BoardDTO dto){
        Board board = Board.create(dto);
        boardRepositroy.save(board);
    }
    //블로그에 작성된 글을 수정
    public void updateBoard(int id,BoardDTO dto){
        Board board = boardRepositroy.findById(id).get();
        //수정값이 있을때만 해당 데이터를 변경 하도록 조건문 사용
        if(dto.getContent() != null){
            board.setContent(dto.getContent());
        }
        if(dto.getTitle() != null){
            board.setTitle(dto.getTitle());
        }
        board.setDate(LocalDate.now());
        boardRepositroy.save(board);
    }
    //블로그에 작성된 글을 삭제
    public void deleteBoard(int id){
        Board board = boardRepositroy.findById(id).get();
        boardRepositroy.delete(board);
    }
    //블로그에 작성된 모든 글을 삭제
    public void deleteAllBoard(){
        boardRepositroy.deleteAll();
    }
}
