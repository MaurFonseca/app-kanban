package com.kanban.app_kanban.service;

import com.kanban.app_kanban.infra.security.SecurityFilter;
import com.kanban.app_kanban.model.entity.Board;
import com.kanban.app_kanban.model.entity.Usuario;
import com.kanban.app_kanban.repository.BoardRepository;
import com.kanban.app_kanban.repository.UsuarioRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;


    private Usuario recoverUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication != null && authentication.getPrincipal() instanceof Usuario usuario){
            return usuario;
        }
        throw new RuntimeException("Usuário não autenticado");
    }

    public List<Board> findAll(){
        Usuario usuario = recoverUser();
        return boardRepository.findAllByUsuarioId(usuario.getId());
    }




}
