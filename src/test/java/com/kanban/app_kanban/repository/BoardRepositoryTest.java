package com.kanban.app_kanban.repository;

import com.kanban.app_kanban.dto.usuario.UsuarioRegisterRequest;
import com.kanban.app_kanban.model.entity.Board;
import com.kanban.app_kanban.model.entity.Usuario;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;



import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
@ActiveProfiles("test")
class BoardRepositoryTest {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private BoardRepository boardRepository;

    @Test
    @DisplayName("Deve retornar sucesso para encontrar um board pelo nome dele e pelo id de usuario")
    void findByNomeAndUsuarioIdCase1() {
        UsuarioRegisterRequest data = new UsuarioRegisterRequest("login@teste", "jose", "123");
        Usuario novo = createUser(data);
        Board board = new Board(null, "teste", novo);
        boardRepository.save(board);

        Board result = boardRepository.findByNomeAndUsuarioId("teste", novo.getId());

        assertNotNull(result);
        assertEquals("teste", result.getNome());
        assertEquals(novo.getId(), result.getUsuario().getId());

    }

    @Test
    @DisplayName("Deve retornar sucesso para não encontrar o dado")
    void findByNomeAndUsuarioIdCas2() {
        UsuarioRegisterRequest data = new UsuarioRegisterRequest("login@teste", "jose", "123");
        Usuario novo = createUser(data);
        Board board = new Board(null, "teste", novo);
        boardRepository.save(board);

        Board result = boardRepository.findByNomeAndUsuarioId("NOME_ERRADO", novo.getId());

        assertNull(result);

    }


    private Usuario createUser(UsuarioRegisterRequest data){
        Usuario newUser = new Usuario(data);
        entityManager.persist(newUser);
        return newUser;
    }
}