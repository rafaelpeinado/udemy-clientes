package io.github.rafaelpeinado.clientes.model.repository;

import io.github.rafaelpeinado.clientes.model.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

}
