package com.kanban.app_kanban.service;

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
        Board board = boardRepository.findByNome(nome);
        if (board.getUsuario().getId().equals(usuarioPermitido.getAuthenticatedUserId().getId())){
            return mapResponses.toBoardResponse(board);
        }
        return null;
    }

    public BoardResponse criarBoard(Board){

    }







}
