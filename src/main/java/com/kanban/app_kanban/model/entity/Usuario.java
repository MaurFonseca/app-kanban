package com.kanban.app_kanban.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kanban.app_kanban.dto.usuario.UsuarioRegisterRequest;
import com.kanban.app_kanban.model.enums.UserRole;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "tb_user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"id", "login"})
@Builder
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @Email
    private String login;

    private String nome;

    private String senha;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    @OneToMany(mappedBy = "usuario")
    @Setter(AccessLevel.NONE)
    @Builder.Default
    @JsonIgnore
    private List<Board> boards = new ArrayList<>();

    public Usuario(String login, String nome, String senha, UserRole role) {
        this.login = login;
        this.nome = nome;
        this.senha = senha;
        this.role = role;
    }

    public Usuario(UsuarioRegisterRequest data) {
        this.login = data.login();
        this.nome = data.nome();
        this.senha = data.senha();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}
