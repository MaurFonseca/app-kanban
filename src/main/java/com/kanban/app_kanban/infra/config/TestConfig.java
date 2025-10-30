package com.kanban.app_kanban.infra.config;

import com.kanban.app_kanban.model.entity.Board;
import com.kanban.app_kanban.model.entity.Usuario;
import com.kanban.app_kanban.model.enums.UserRole;
import com.kanban.app_kanban.repository.BoardRepository;
import com.kanban.app_kanban.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private BoardRepository boardRepository;
    @Override
    public void run(String... args) throws Exception {
        Usuario u1 = new Usuario("maur@teste", "teste", new BCryptPasswordEncoder().encode("123"), UserRole.USER);
        usuarioRepository.save(u1);

        Board b1 = new Board(null, "teste", u1);
        boardRepository.save(b1);


    }
}
