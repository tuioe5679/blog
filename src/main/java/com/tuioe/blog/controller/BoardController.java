package com.tuioe.blog.controller;

import com.tuioe.blog.Entity.Board;
import com.tuioe.blog.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class BoardController {

    @Autowired
    private BoardService boardService;

    @RequestMapping(value = "/boards",method = RequestMethod.GET)
    public ResponseEntity list(){
        List<Board> board = boardService.findAllBoard();
        return new ResponseEntity(board, HttpStatus.OK);
    }
    @RequestMapping(value = "/board",method = RequestMethod.POST)
    public ResponseEntity boardAdd(@RequestBody Board board){
        boardService.creatBoard(board);
        return new ResponseEntity("Create",HttpStatus.OK);
    }
}
