package br.com.fiap.asssistenciaco.repository;

import br.com.fiap.asssistenciaco.entity.Observacao;
import br.com.fiap.asssistenciaco.entity.OrdemServico;
import br.com.fiap.asssistenciaco.entity.Tecnico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public interface OrdemServicoRepository extends JpaRepository<OrdemServico, Integer>, JpaSpecificationExecutor<OrdemServico> {

    List<OrdemServico> findByDataEntradaBetween(LocalDateTime dataInicio, LocalDateTime dataFim);

    @Query("select os from OrdemServico os " +
           "where os.prioridade = br.com.fiap.asssistenciaco.enums.PrioridadeExecucaoEnum.CRITICA")
    List<OrdemServico> findCriticas();


    @Query("select count(os) from OrdemServico os " +
           "where os.status = br.com.fiap.asssistenciaco.enums.StatusExecucaoEnum.CONCLUIDO")
    Long countConcluidas();

    @Query("select os from OrdemServico os where os.equipamento.numeroSerie = :numeroSerie")
    List<OrdemServico> findByNumeroSerie(String numeroSerie);

    @Query("select obs from Observacao obs where obs.ordemServico.id = :idOS")
    List<Observacao> findObservacoes(Integer idOS);

    //Total a pagar de uma determinada OS (por id)
    @Query("select sum(is.valor) from ItemServico is where is.ordemServico.id = :idOS")
    BigDecimal getTotalById(Integer idOS);

}
