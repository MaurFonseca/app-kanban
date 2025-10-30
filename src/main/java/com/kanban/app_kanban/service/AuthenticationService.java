package com.kanban.app_kanban.service;

import com.kanban.app_kanban.dto.usuario.UsuarioLoginRequest;
import com.kanban.app_kanban.dto.usuario.UsuarioRegisterRequest;
import com.kanban.app_kanban.model.entity.Usuario;
import com.kanban.app_kanban.repository.UsuarioRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@Service
public class AuthenticationService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public ResponseEntity login(UsuarioLoginRequest request){
        var usernamePassword = new UsernamePasswordAuthenticationToken(request.login(), request.senha());

        var auth = this.authenticationManager.authenticate(usernamePassword);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity register(UsuarioRegisterRequest register){
        if(usuarioRepository.findByLogin(register.login()) != null){
            throw  new RuntimeException("Login já cadastrado");
        }
        String encrypted = new BCryptPasswordEncoder().encode(register.senha());
        Usuario novo = Usuario.builder()
                .nome(register.nome())
                .login(register.login())
                .senha(encrypted)
                .build();
        usuarioRepository.save(novo);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/register")
                .buildAndExpand()
                .toUri();
        return ResponseEntity.created(uri).build();
    }
}
