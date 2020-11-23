package net.crudclientespringboot.repository;

import net.crudclientespringboot.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository  extends JpaRepository<Usuario, Long> {

    @Query("select u from Usuario u where u.login like ?1")
    Optional<Usuario> buscaPorLogin(String username);

    @Query("select u from Usuario u where u.login like ?1 and u.senha like ?2")
    Optional<Usuario> buscaPorLoginAndSenha(String username, String password);
}

