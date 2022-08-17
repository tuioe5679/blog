package com.tuioe.blog.service;

import com.tuioe.blog.Entity.Board;
import com.tuioe.blog.dto.BoardDTO;
import com.tuioe.blog.repositroy.BoardRepositroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class BoardService {

    @Autowired
    private BoardRepositroy boardRepositroy;

    public List<BoardDTO> findAllBoard(){
        List<Board> boards =boardRepositroy.findAll();
        List<BoardDTO> responseDTOS = new ArrayList<>();
        for(Board board: boards){
            responseDTOS.add(BoardDTO.create(board));
        }
        return responseDTOS;
    }

    public BoardDTO findBoard(int id){
        return BoardDTO.create(boardRepositroy.findById(id).get());
    }

    public void createBoard(BoardDTO dto){
        Board board = Board.create(dto);
        boardRepositroy.save(board);
    }

    public void updateBoard(int id,BoardDTO dto){
        Board board = boardRepositroy.findById(id).get();
        if(dto.getContent() != null){
            board.setContent(dto.getContent());
        }
        if(dto.getTitle() != null){
            board.setTitle(dto.getTitle());
        }
        board.setDate(LocalDate.now());
        boardRepositroy.save(board);
    }

    public void deleteBoard(int id){
        Board board = boardRepositroy.findById(id).get();
        boardRepositroy.delete(board);
    }

    public void deleteAllBoard(){
        boardRepositroy.deleteAll();
    }
}
