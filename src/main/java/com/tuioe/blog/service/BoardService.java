package com.tuioe.blog.service;

import com.tuioe.blog.Entity.Board;
import com.tuioe.blog.repositroy.BoardRepositroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class BoardService {

    @Autowired
    private BoardRepositroy boardRepositroy;

    public List<Board> findAllBoard(){
        return boardRepositroy.findAll();
    }

    public Board findBoard(int id){
        return boardRepositroy.findById(id).get();
    }

    public void creatBoard(Board board){
        board.setDate(LocalDate.now());
        boardRepositroy.save(board);
    }

    public void updateBoard(int id,Board board){
        Board udBoard = boardRepositroy.findById(id).get();
        udBoard.setTitle(board.getTitle());
        udBoard.setContent(board.getContent());
        udBoard.setName(board.getName());
        udBoard.setHits(board.getHits());
        udBoard.setDate(LocalDate.now());
        boardRepositroy.save(udBoard);
    }

    public void deleteBoard(int id){
        Board board = boardRepositroy.findById(id).get();
        boardRepositroy.delete(board);
    }

    public void deleteAllBoard(){
        boardRepositroy.deleteAll();
    }


}
