package io.github.rafaelpeinado.clientes.model.repository;

import io.github.rafaelpeinado.clientes.model.entity.Servico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServicoRepository extends JpaRepository<Servico, Integer> {

}
