package br.com.fiap.asssistenciaco.service;

import br.com.fiap.asssistenciaco.dto.ServicoAtualizacaoDTO;
import br.com.fiap.asssistenciaco.dto.ServicoInsercaoDTO;
import br.com.fiap.asssistenciaco.dto.ServicoResponseDTO;
import br.com.fiap.asssistenciaco.entity.Servico;
import br.com.fiap.asssistenciaco.repository.ServicoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ServicoService {

    @Autowired
    private ServicoRepository servicoRepository;

    private ModelMapper mapper = new ModelMapper();

    public List<ServicoResponseDTO> buscarTodos(PageRequest pageRequest) {
        List<Servico> resultado = servicoRepository.findAll();
        return resultado.stream()
                .map(e -> mapper.map(e, ServicoResponseDTO.class))
                .collect(Collectors.toList());
    }

    public Optional<ServicoResponseDTO> buscarPorID(Integer id) {
        var resultado = servicoRepository.findById(id);
        if (resultado.isEmpty()){
            return Optional.empty();
        }
        var dto = mapper.map(resultado.get(), ServicoResponseDTO.class);
        return Optional.of(dto);
    }

    public ServicoResponseDTO inserir(ServicoInsercaoDTO dto){
        var existe = servicoRepository.findByDescricaoIgnoreCase(dto.getDescricao());
        if (!existe.isEmpty()) {
            throw new RuntimeException("Já existe um serviço cadastrado com essa descrição");
        }
        var entidade = mapper.map(dto, Servico.class);
        var entidadeSalva = servicoRepository.save(entidade);
        return mapper.map(entidadeSalva, ServicoResponseDTO.class);
    }

    public Optional<ServicoResponseDTO> atualizar(Integer id, ServicoAtualizacaoDTO dto){
        var resultado = servicoRepository.findById(id);
        if (resultado.isEmpty()){
            return Optional.empty();
        }

        var existente = servicoRepository.findByDescricaoIgnoreCaseAndIdNot(dto.getDescricao(), id);
        if (!existente.isEmpty()){
            throw new RuntimeException("Já existe um serviço cadastrado com essa descrição");
        }

        var entidade = mapper.map(dto, Servico.class);
        var entidadeSalva = servicoRepository.save(entidade);
        return Optional.of(mapper.map(entidadeSalva, ServicoResponseDTO.class));
    }

    public Optional<ServicoResponseDTO> excluir (Integer id) {
        Optional<Servico> resultado = servicoRepository.findById(id);
        if (resultado.isEmpty()) {
            return Optional.empty();
        }
        var itensExistentes = servicoRepository.findItensServico(id);
        if(!itensExistentes.isEmpty()){
            throw new RuntimeException("Esse serviço já foi incluido em uma OS");
        }
        servicoRepository.deleteById(id);
        return Optional.of(new ServicoResponseDTO());
    }
}
