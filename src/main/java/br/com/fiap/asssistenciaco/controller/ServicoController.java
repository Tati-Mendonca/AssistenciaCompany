package br.com.fiap.asssistenciaco.controller;

import br.com.fiap.asssistenciaco.entity.Servico;
import br.com.fiap.asssistenciaco.repository.ServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
