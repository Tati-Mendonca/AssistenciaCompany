package br.com.fiap.asssistenciaco.controller;

import br.com.fiap.asssistenciaco.dto.FiltroOSDTO;
import br.com.fiap.asssistenciaco.dto.OrdemServicoInsercaoDTO;
import br.com.fiap.asssistenciaco.dto.OrdemServicoResponseDTO;
import br.com.fiap.asssistenciaco.service.OrdemServicoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;


@RestController
@RequestMapping("/ordem-servico")
@CrossOrigin(origins = "*", maxAge = 3600)
@Tag(name = "/ordem-servico", description = "Gerência as ordens de serviços")
public class OrdemServicoController {

    @Autowired
    private OrdemServicoService osService;

    @GetMapping
    @Operation(summary = "Consulta ordens de serviços", description = "Consulta todas as OS disponíveis")
    @ApiResponse(responseCode = "200", description = "OS encontradas com sucesso")
    @ApiResponse(responseCode = "500", description = "Erro interno da aplicação")
    public List<OrdemServicoResponseDTO> listarTodos(@RequestParam(required = false) String documento,
                                                     @RequestParam(required = false) String nome,
                                                     @RequestParam(required = false) String modelo,
                                                     @RequestParam(required = false) LocalDate dataInicio,
                                                     @RequestParam(required = false) LocalDate dataFim) {
        var filtro = FiltroOSDTO.builder().documento(documento)
                    .nome(nome)
                    .modelo(modelo)
                    .dataInicio(dataInicio)
                    .dataFim(dataFim)
                .build();
        return osService.listarTodos(filtro);
    }


    @PostMapping
    @Operation(summary = "Insere ordem de serviço", description = "Insere um nova OS")
    @ApiResponse(responseCode = "200", description = "OS inserida com sucesso")
    public ResponseEntity<OrdemServicoResponseDTO> inserir(@RequestBody @Valid OrdemServicoInsercaoDTO body) {
        var salvo = osService.inserir(body);
        return ResponseEntity.ok(salvo);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Consulta OS por ID", description = "Realiza consulta de OS por ID ")
    @ApiResponse(responseCode = "200", description = "OS encontrada com sucesso")
    public ResponseEntity<OrdemServicoResponseDTO> buscarPorId(@PathVariable Integer id) {
        var resultado = osService.buscarPorId(id);
        if (resultado.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(resultado.get());
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Finaliza uma OS", description = "Finaliza uma OS aberta")
    @ApiResponse(responseCode = "200", description = "OS finalizada com sucesso")
    public ResponseEntity<OrdemServicoResponseDTO> finalizar(@PathVariable Integer id) {
        var resultado = osService.finalizar(id);
        if (resultado.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(resultado.get());
    }


}
