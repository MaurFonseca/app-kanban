package com.kanban.app_kanban.controller;

import com.kanban.app_kanban.dto.usuario.UsuarioLoginRequest;
import com.kanban.app_kanban.dto.usuario.UsuarioRegisterRequest;
import com.kanban.app_kanban.service.AuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity login(@Valid @RequestBody UsuarioLoginRequest request){
        return authenticationService.login(request);
    }

    @PostMapping("/register")
    public ResponseEntity register(@Valid @RequestBody UsuarioRegisterRequest register){
        return authenticationService.register(register);
    }
}
