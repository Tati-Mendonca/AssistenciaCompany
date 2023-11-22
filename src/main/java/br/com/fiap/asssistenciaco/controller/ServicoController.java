package br.com.fiap.asssistenciaco.controller;

import br.com.fiap.asssistenciaco.dto.ServicoAtualizacaoDTO;
import br.com.fiap.asssistenciaco.dto.ServicoInsercaoDTO;
import br.com.fiap.asssistenciaco.dto.ServicoResponseDTO;
import br.com.fiap.asssistenciaco.service.ServicoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@RestController
@RequestMapping("/servicos")
@CrossOrigin(origins = "*", maxAge = 3600)
@Tag(name = "/servicos", description = "Gerência os serviços disponíbilizados pela assistência técnica")

public class ServicoController {

    @Autowired
    private ServicoService servicoService;

    @GetMapping
    @Operation(summary = "Consulta todos os serviços", description = "Consulta todos os serviços disponíveis")
    @ApiResponse(responseCode = "200", description = "Serviços encontrados com sucesso")
    @ApiResponse(responseCode = "500", description = "Erro interno da aplicação")
    public List<ServicoResponseDTO> buscarTodos(@RequestParam(defaultValue = "0") int pagina, @RequestParam(defaultValue = "5") int tamanho){
        var pageRequest = PageRequest.of(pagina, tamanho);
        return servicoService.buscarTodos(pageRequest);
    }

    @Operation(summary = "Consulta por ID", description = "Realiza consulta de serviço por ID ")
    @ApiResponse(responseCode = "200", description = "Serviço encontrado com sucesso")
    @GetMapping("/{id}")
    public ResponseEntity<ServicoResponseDTO> buscarPorId(@PathVariable Integer id){
        var resultado = servicoService.buscarPorID(id);
        if (resultado.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(resultado.get());
    }

    @PostMapping
    @Operation(summary = "Insere serviços", description = "Insere um novo serviço")
    @ApiResponse(responseCode = "200", description = "Serviço inserido com sucesso")
        public ResponseEntity<ServicoResponseDTO> inserir(@RequestBody @Valid ServicoInsercaoDTO body){
        var salvo = servicoService.inserir(body);
        return ResponseEntity.ok(salvo);
    }


    @Operation(summary = "Atualiza serviços", description = "Atualiza um serviço por ID ")
    @ApiResponse(responseCode = "200", description = "Serviço atualizado com sucesso")
    @PutMapping("/{id}")
    public ResponseEntity<ServicoResponseDTO> atualizar(@PathVariable Integer id,
                                             @RequestBody @Valid ServicoAtualizacaoDTO body){
        var resultado = servicoService.atualizar(id, body);
        if (resultado.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(resultado.get());
    }


    @Operation(summary = "Exclui serviços", description = "Exclui um serviço por ID ")
    @ApiResponse(responseCode = "200", description = "Serviço excluido com sucesso")
    @DeleteMapping("/{id}")
    public ResponseEntity excluir (@PathVariable Integer id){
        var resultado = servicoService.excluir(id);
       if (resultado.isEmpty()){
           return  ResponseEntity.notFound().build();
       }

        return  ResponseEntity.ok().build();
    }




}
