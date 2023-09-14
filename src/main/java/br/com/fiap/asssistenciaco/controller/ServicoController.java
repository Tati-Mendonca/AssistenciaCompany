package br.com.fiap.asssistenciaco.controller;

import br.com.fiap.asssistenciaco.entity.Servico;
import br.com.fiap.asssistenciaco.repository.ServicoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/servicos")
public class ServicoController {

    @Autowired
    private ServicoRepository servicoRepository;

    @GetMapping
    public List<Servico> buscarTodos(){
        return servicoRepository.findAll();

    }

    @GetMapping("/{id}")
    public ResponseEntity<Servico> buscarPorId(@PathVariable Integer id){
        var resultado = servicoRepository.findById(id);
        if (resultado.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(resultado.get());
    }

    @PostMapping
    public ResponseEntity<Servico> inserir(@RequestBody @Valid Servico body){
        var existente = servicoRepository.findByDescricaoIgnoreCase(body.getDescricao());
        if (!existente.isEmpty()){
            throw new RuntimeException("Já existe um serviço cadastrado para essa descrição");
        }

        var salvo = servicoRepository.save(body);
        return ResponseEntity.ok(salvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Servico> atualizar(@PathVariable Integer id,
                                             @RequestBody Servico body){
        var resultado = servicoRepository.findById(id);
        if (resultado.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        var existente = servicoRepository.findByDescricaoIgnoreCaseAndIdNot(body.getDescricao(), id);
        if (!existente.isEmpty()){
            return  ResponseEntity.badRequest().build();
        }

        var salvo = servicoRepository.save(body);
        return ResponseEntity.ok(salvo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity excluir (@PathVariable Integer id){
        Optional<Servico> resultado = servicoRepository.findById(id);
        if (resultado.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var itensExistentes = servicoRepository.findItensServico(id);
        if(!itensExistentes.isEmpty()){
            return ResponseEntity.badRequest().build();
        }

        servicoRepository.deleteById(id);
        return  ResponseEntity.ok().build();
    }


}
