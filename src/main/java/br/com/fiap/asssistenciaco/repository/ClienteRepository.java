package br.com.fiap.asssistenciaco.repository;

import br.com.fiap.asssistenciaco.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

}
