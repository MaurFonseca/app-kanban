package com.kanban.app_kanban.service;

import com.kanban.app_kanban.dto.board.BoardRequest;
import com.kanban.app_kanban.dto.board.BoardResponse;
import com.kanban.app_kanban.model.entity.Board;
import com.kanban.app_kanban.model.entity.Usuario;
import com.kanban.app_kanban.repository.BoardRepository;
import com.kanban.app_kanban.repository.UsuarioRepository;
import com.kanban.app_kanban.service.utilitario.MapResponses;
import com.kanban.app_kanban.service.utilitario.UsuarioPermitido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioPermitido usuarioPermitido;

    @Autowired
    private MapResponses mapResponses;

    public List<BoardResponse> findAll(){
       Usuario usuario = usuarioPermitido.getAuthenticatedUserId();
       List<Board> boards = boardRepository.findAllByUsuarioId(usuario.getId());
       List<BoardResponse> boardResponses = boards.stream().map(x -> mapResponses.toBoardResponse(x)).toList();
        return boardResponses;
    }

    public BoardResponse findByNome(String nome){
        Board board = boardRepository.findByNomeAndUsuarioId(nome, usuarioPermitido.getAuthenticatedUserId().getId());
        if (board.getUsuario().getId().equals(usuarioPermitido.getAuthenticatedUserId().getId())){
            return mapResponses.toBoardResponse(board);
        }
        return null;
    }

    public BoardResponse criarBoard(BoardRequest request){
        Board board = new Board(null, request.nome(), usuarioPermitido.getAuthenticatedUserId());
        boardRepository.save(board);
        return mapResponses.toBoardResponse(board);
    }

    public BoardResponse atualizarBoard(String nome, BoardRequest request){
        Board board = boardRepository.findByNomeAndUsuarioId(nome, usuarioPermitido.getAuthenticatedUserId().getId() );
        if (!board.getUsuario().equals(usuarioPermitido.getAuthenticatedUserId())){
            throw new RuntimeException("Acesso Negado");
        }
        board.setNome(request.nome());
        boardRepository.save(board);
        return mapResponses.toBoardResponse(board);
    }







}
