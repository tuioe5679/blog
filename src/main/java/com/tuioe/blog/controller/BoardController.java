package com.tuioe.blog.controller;

import com.tuioe.blog.dto.BoardDTO;
import com.tuioe.blog.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class BoardController {

    @Autowired
    private BoardService boardService;

    @RequestMapping(value = "/boards",method = RequestMethod.GET)
    public ResponseEntity list(){
        return new ResponseEntity(boardService.findAllBoard(), HttpStatus.OK);
    }

    @RequestMapping(value = "/board/{id}",method = RequestMethod.GET)
    public ResponseEntity findBoard(@PathVariable int id){
        return new ResponseEntity(boardService.findBoard(id),HttpStatus.OK);
    }

    @RequestMapping(value = "/board",method = RequestMethod.POST)
    public ResponseEntity boardAdd(@RequestBody BoardDTO dto){
        boardService.createBoard(dto);
        return new ResponseEntity("Create",HttpStatus.CREATED);
    }

    @RequestMapping(value = "/board/{id}",method = RequestMethod.PUT)
    public ResponseEntity boardUpdate(@PathVariable int id,@RequestBody BoardDTO dto){
        boardService.updateBoard(id,dto);
        return new ResponseEntity("Update",HttpStatus.OK);
    }

    @RequestMapping(value = "/board/{id}",method = RequestMethod.DELETE)
    public ResponseEntity boardDelete(@PathVariable int id){
        boardService.deleteBoard(id);
        return new ResponseEntity("Delete",HttpStatus.OK);
    }
    
    @RequestMapping(value = "/boards",method = RequestMethod.DELETE)
    public ResponseEntity boardAllDelete(){
        boardService.deleteAllBoard();
        return new ResponseEntity("AllDelete",HttpStatus.OK);
    }
}
