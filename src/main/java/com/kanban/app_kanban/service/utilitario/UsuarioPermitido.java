package com.kanban.app_kanban.service.utilitario;

import com.kanban.app_kanban.model.entity.Usuario;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class UsuarioPermitido {

    public Usuario getAuthenticatedUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication != null && authentication.getPrincipal() instanceof Usuario usuario){
            return usuario;
        }
        throw new RuntimeException("Usuário não autenticado");
    }

}
