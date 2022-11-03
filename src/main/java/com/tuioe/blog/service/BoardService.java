package com.tuioe.blog.service;

import com.tuioe.blog.domain.Entity.Board;
import com.tuioe.blog.domain.Entity.Member;
import com.tuioe.blog.domain.repositroy.BoardRepositroy;
import com.tuioe.blog.domain.repositroy.MemberRepositroy;
import com.tuioe.blog.dto.board.BoardListResponseDto;
import com.tuioe.blog.dto.board.BoardRequestDto;
import com.tuioe.blog.dto.board.BoardResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    private String username;

    private final BoardRepositroy boardRepositroy;

    private final MemberRepositroy memberRepositroy;

    //블로그의 모든 글을 반환
    @Transactional
    public List<BoardListResponseDto> findAllBoard(){
        List<Board> boards = boardRepositroy.findAll();
        List<BoardListResponseDto> responseDTOS = new ArrayList<>();
        for(Board board: boards){ // 향상된 for문 사용 주로 배열에 사용
            BoardListResponseDto boardDTO = new BoardListResponseDto(board);
            responseDTOS.add(boardDTO);
        }
        return responseDTOS;
    }
    //블로그에 작성된 특정 글을 반환
    @Transactional
    public BoardResponseDto findBoard(Long id){
        boardRepositroy.updateHits(id);
        Board board = boardRepositroy.findById(id).get();
        return new BoardResponseDto(board);
    }

    public void findUserName(String username){
        this.username = username;
    }

    //블로그에 글 작성
    @Transactional
    public void createBoard(BoardRequestDto dto){
        Board board = dto.toEntity();
        Member member = memberRepositroy.findByEmail(username);
        board.setMember(member);
        boardRepositroy.save(board);
    }

    //블로그에 작성된 글을 수정
    @Transactional
    public void updateBoard(Long id, BoardRequestDto dto){
        Board board = boardRepositroy.findById(id).get();
        board.update(dto);
        boardRepositroy.save(board);
    }

    //블로그에 작성된 글을 삭제
    @Transactional
    public void deleteBoard(Long id){
        Board board = boardRepositroy.findById(id).get();
        boardRepositroy.delete(board);
    }
    //블로그에 작성된 모든 글을 삭제
    @Transactional
    public void deleteAllBoard(){
        boardRepositroy.deleteAll();
    }
}
