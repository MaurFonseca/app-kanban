package com.kanban.app_kanban.service;

import com.kanban.app_kanban.dto.usuario.UsuarioLoginRequest;
import com.kanban.app_kanban.dto.usuario.UsuarioLoginResponse;
import com.kanban.app_kanban.dto.usuario.UsuarioRegisterRequest;
import com.kanban.app_kanban.model.entity.Usuario;
import com.kanban.app_kanban.model.enums.UserRole;
import com.kanban.app_kanban.repository.UsuarioRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
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

    @Autowired
    private TokenService tokenService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public ResponseEntity login(UsuarioLoginRequest request){
        var usernamePassword = new UsernamePasswordAuthenticationToken(request.login(), request.senha());

        try {
            var auth = this.authenticationManager.authenticate(usernamePassword);
            var token = tokenService.tokenGeneration((Usuario) auth.getPrincipal());
            return ResponseEntity.ok().body(new UsuarioLoginResponse(token));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(401).body("Usuário ou senha inválidos");
        }
    }

    public ResponseEntity register(UsuarioRegisterRequest register){
        if(usuarioRepository.findByLogin(register.login()) != null){
            throw  new RuntimeException("Login já cadastrado");
        }
        String encrypted = passwordEncoder.encode(register.senha());
        Usuario novo = Usuario.builder()
                .nome(register.nome())
                .login(register.login())
                .senha(encrypted)
                .role(UserRole.USER)
                .build();
        usuarioRepository.save(novo);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/register")
                .buildAndExpand()
                .toUri();
        return ResponseEntity.created(uri).build();
    }
}
