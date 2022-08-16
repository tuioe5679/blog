package com.tuioe.blog.controller;

import com.tuioe.blog.Entity.Board;
import com.tuioe.blog.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(value = "/board/{id}",method = RequestMethod.PUT)
    public ResponseEntity boardUpdate(@PathVariable int id,@RequestBody Board board){
        boardService.updateBoard(id,board);
        return new ResponseEntity("Update",HttpStatus.OK);
    }

    @RequestMapping(value = "/board/{id}",method = RequestMethod.DELETE)
    public ResponseEntity boardDelete(@PathVariable int id){
        boardService.deleteBoard(id);
        return new ResponseEntity("Delete",HttpStatus.OK);
    }


}
