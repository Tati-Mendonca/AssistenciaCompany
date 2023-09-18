package br.com.fiap.asssistenciaco.service;

import br.com.fiap.asssistenciaco.dto.OrdemServicoInsercaoDTO;
import br.com.fiap.asssistenciaco.dto.OrdemServicoResponseDTO;
import br.com.fiap.asssistenciaco.entity.Cliente;
import br.com.fiap.asssistenciaco.entity.Equipamento;
import br.com.fiap.asssistenciaco.entity.Observacao;
import br.com.fiap.asssistenciaco.entity.OrdemServico;
import br.com.fiap.asssistenciaco.enums.PrioridadeExecucaoEnum;
import br.com.fiap.asssistenciaco.enums.StatusExecucaoEnum;
import br.com.fiap.asssistenciaco.enums.TipoDocumentoEnum;
import br.com.fiap.asssistenciaco.repository.ClienteRepository;
import br.com.fiap.asssistenciaco.repository.EquipamentoRepository;
import br.com.fiap.asssistenciaco.repository.ObservacaoRepository;
import br.com.fiap.asssistenciaco.repository.OrdemServicoRepository;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class OrdemServicoService {

    private static final Set<StatusExecucaoEnum> STATUS_PERMITEM_FINALIZACAO = Set.of(StatusExecucaoEnum.ABERTO, StatusExecucaoEnum.EM_ANDAMENTO);

    @Autowired
    private OrdemServicoRepository osRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EquipamentoRepository equipamentoRepository;

    @Autowired
    private ObservacaoRepository observacaoRepository;

    private ModelMapper mapper = new ModelMapper();


    public OrdemServicoResponseDTO inserir(OrdemServicoInsercaoDTO request) {
        var cliente = Cliente.builder().tipoDocumento(TipoDocumentoEnum.CPF)
                                       .nome(request.getCliente())
                             .build();
        clienteRepository.save(cliente);

        var equipamento = mapper.map(request.getEquipamento(), Equipamento.class);
        equipamento.setNumeroSerie(String.valueOf(System.currentTimeMillis()));
        equipamentoRepository.save(equipamento);

        var entity = mapper.map(request, OrdemServico.class);
        entity.setCliente(cliente);
        entity.setEquipamento(equipamento);
        entity.setStatus(StatusExecucaoEnum.ABERTO);
        entity.setPrioridade(PrioridadeExecucaoEnum.BAIXA);
        osRepository.save(entity);

        if (StringUtils.isNotBlank(request.getObservacoes())) {
            var observacao = Observacao.builder().ordemServico(entity)
                                                 .texto(request.getObservacoes())
                                                 .data(entity.getDataEntrada())
                                       .build();
            observacaoRepository.save(observacao);
        }

        return mapper.map(entity, OrdemServicoResponseDTO.class);
    }

    public Optional<OrdemServicoResponseDTO> buscarPorId(Integer id) {
        var resultado = osRepository.findById(id);
        if (resultado.isEmpty()) {
            return Optional.empty();
        }
        var dto = mapper.map(resultado.get(), OrdemServicoResponseDTO.class);
        var observaoes = osRepository.findObservacoes(id).stream()
                                                         .map(Observacao::getTexto)
                                                         .collect(Collectors.joining(" | "));
        dto.setObservacoes(observaoes);
        return Optional.of(dto);
    }

    public List<OrdemServicoResponseDTO> listarTodos() {
        var resultado = osRepository.findAll();
        return resultado.stream().map(e -> mapper.map(e, OrdemServicoResponseDTO.class)).toList();
    }

    public Optional<OrdemServicoResponseDTO> finalizar(Integer id) {
        var resultado = osRepository.findById(id);
        if (resultado.isEmpty()) {
            return Optional.empty();
        }

        var entity = resultado.get();
        if (!STATUS_PERMITEM_FINALIZACAO.contains(entity.getStatus())) {
            throw new RuntimeException("Ordem de serviço com status " + entity.getStatus());
        }

        entity.setStatus(StatusExecucaoEnum.CONCLUIDO);
        entity.setDataSaida(LocalDateTime.now());
        osRepository.save(entity);

        return Optional.of(mapper.map(entity, OrdemServicoResponseDTO.class));
    }


}
