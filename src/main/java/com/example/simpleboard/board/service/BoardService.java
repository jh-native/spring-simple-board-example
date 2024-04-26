package com.example.simpleboard.board.service;

import com.example.simpleboard.board.db.BoardEntity;
import com.example.simpleboard.board.db.BoardRepository;
import com.example.simpleboard.board.model.BoardDto;
import com.example.simpleboard.board.model.BoardRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    private final BoardConverter boardConverter;

    public BoardDto create (BoardRequest boardRequest) {
        var entity = BoardEntity.builder()
                .id(1L)
                .boardName(boardRequest.getBoardName())
                .status("REGISTERED")
                .build();

        boardRepository.save(entity);
        // var saveEntity = boardRepository.save(entity);

        return boardConverter.toDto(entity);
    }

    public BoardDto view(Long id) {
        return boardConverter.toDto(boardRepository.findById(id).get());
    }
}
