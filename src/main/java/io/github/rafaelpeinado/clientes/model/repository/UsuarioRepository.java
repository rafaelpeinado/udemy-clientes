package io.github.rafaelpeinado.clientes.model.repository;

import io.github.rafaelpeinado.clientes.model.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
}
