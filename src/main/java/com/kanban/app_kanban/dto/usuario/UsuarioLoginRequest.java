package com.kanban.app_kanban.dto.usuario;

import jakarta.validation.constraints.NotBlank;

public record UsuarioLoginRequest (String login, @NotBlank(message = "Senha não enviada")String senha){
}
