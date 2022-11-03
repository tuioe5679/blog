package com.tuioe.blog.controller;

import com.tuioe.blog.dto.board.BoardRequestDto;
import com.tuioe.blog.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/boards")
    public ResponseEntity findBoardList(){
        return new ResponseEntity(boardService.findAllBoard(), HttpStatus.OK);
    }

    @GetMapping("/board/{id}")
    public ResponseEntity findBoard(@PathVariable Long id){
        return new ResponseEntity(boardService.findBoard(id),HttpStatus.OK);
    }

    @PostMapping("/board")
    public ResponseEntity boardAdd(@RequestBody BoardRequestDto dto){
        boardService.createBoard(dto);
        return new ResponseEntity("Create",HttpStatus.CREATED);
    }

    @PutMapping("/board/{id}")
    public ResponseEntity boardUpdate(@PathVariable Long id,@RequestBody BoardRequestDto dto){
        boardService.updateBoard(id,dto);
        return new ResponseEntity("Update",HttpStatus.OK);
    }

    @DeleteMapping("/board/{id}")
    public ResponseEntity boardDelete(@PathVariable Long id){
        boardService.deleteBoard(id);
        return new ResponseEntity("Delete",HttpStatus.OK);
    }
    
    @DeleteMapping("/boards")
    public ResponseEntity boardAllDelete(){
        boardService.deleteAllBoard();
        return new ResponseEntity("AllDelete",HttpStatus.OK);
    }
}
