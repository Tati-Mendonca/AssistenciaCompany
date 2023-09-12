package br.com.fiap.asssistenciaco.repository;

import br.com.fiap.asssistenciaco.entity.ItemServico;
import br.com.fiap.asssistenciaco.entity.Servico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;

public interface ServicoRepository extends JpaRepository<Servico, Integer> {

    List<Servico> findByValorGreaterThanEqual(BigDecimal valor);


    @Query("select is from ItemServico is where is.servico.id = :id")
    List<ItemServico> findItensServico(Integer id);


    List<Servico> findByDescricaoIgnoreCase(String descricao);


    List<Servico> findByDescricaoIgnoreCaseAndIdNot(String descricao, Integer id);


}
