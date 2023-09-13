package br.com.fiap.asssistenciaco.controller;

import br.com.fiap.asssistenciaco.entity.Servico;
import br.com.fiap.asssistenciaco.repository.ServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<Servico> inserir(@RequestBody Servico body){
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
        var resultado = servicoRepository.findById(id);
        if (resultado.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        servicoRepository.deleteById(id);
        return  ResponseEntity.ok().build();
    }


}
