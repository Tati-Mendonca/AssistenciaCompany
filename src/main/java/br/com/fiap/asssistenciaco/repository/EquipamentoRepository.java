package br.com.fiap.asssistenciaco.repository;

import br.com.fiap.asssistenciaco.entity.Equipamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EquipamentoRepository extends JpaRepository<Equipamento, Integer> {
}
