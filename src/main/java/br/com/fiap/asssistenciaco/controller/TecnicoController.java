package br.com.fiap.asssistenciaco.controller;

import br.com.fiap.asssistenciaco.entity.Tecnico;
import br.com.fiap.asssistenciaco.repository.TecnicoRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tecnicos")
@CrossOrigin(origins = "*", maxAge = 3600)
@Tag(name = "/tecnicos", description = "Gerencia os técnicos disponíveis")
public class TecnicoController {

    @Autowired
    private TecnicoRepository tecnicoRepository;

    @GetMapping
    @ApiResponse(responseCode = "200", description = "Técnicos encontrados com sucesso")
    @ApiResponse(responseCode = "500", description = "Erro interno da aplicação")
    @Operation(summary = "Consulta todos os Técnicos", description = "Consulta todos os técnicos disponíveis")
    public Page<Tecnico> listarTodos(@RequestParam(required = false) String email,
                                         @RequestParam(defaultValue = "0") int pagina,
                                         @RequestParam(defaultValue = "5") int tamanho) {
        var pageRequest = PageRequest.of(pagina, tamanho);

        if (email == null) {
            return tecnicoRepository.findAll(pageRequest);
        }

        return tecnicoRepository.findByEmail(email, pageRequest);

    }




    @GetMapping("/{id}")
    @Operation(summary = "Consulta por ID", description = "Realiza consulta de tecnicos por ID ")
    @ApiResponse(responseCode = "200", description = "Técnico encontrado com sucesso")
    public ResponseEntity<Tecnico> buscarPorId(@PathVariable Integer id) {
        var resultado = tecnicoRepository.findById(id);
        if (resultado.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(resultado.get());
    }

    @PostMapping
    @Operation(summary = "Insere técnicos", description = "Insere um novo técnico")
    @ApiResponse(responseCode = "200", description = "Técnico inserido com sucesso")
    public ResponseEntity<Tecnico> inserir(@RequestBody @Valid Tecnico tecnico) {
        var existe = tecnicoRepository.findByEmail(tecnico.getEmail());
        if (existe.isPresent()) {
            throw new RuntimeException("E-mail já cadastrado");
        }
        var salvo = tecnicoRepository.save(tecnico);
        return ResponseEntity.ok(salvo);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza técnico", description = "Atualiza informações de um técnico")
    @ApiResponse(responseCode = "200", description = "Informações atualizadas com sucesso")
    public ResponseEntity<Tecnico> atualizar(@PathVariable Integer id,
                                             @RequestBody @Valid Tecnico body) {
        var resultado = tecnicoRepository.findById(id);
        if (resultado.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        var existente = tecnicoRepository.findByEmailIgnoreCaseAndIdNot(body.getEmail(), id);
        if (!existente.isEmpty()) {
            throw new RuntimeException("Já existe um e-mail cadastrado");
        }

        var salvo = tecnicoRepository.save(body);
        return ResponseEntity.ok(salvo);
    }


    @DeleteMapping("/{id}")
    @Operation(summary = "Exclui técnico", description = "Exclui cadastro de um técnico")
    @ApiResponse(responseCode = "200", description = "Cadastro excluído com sucesso")
    public ResponseEntity excluir(@PathVariable Integer id) {
        var resultado = tecnicoRepository.findById(id);
        if (resultado.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        var existeOS = tecnicoRepository.findOrdensServicoBy(id);
        if (!existeOS.isEmpty()) {
            throw new RuntimeException("Técnico com OS vinculada.");
        }

        tecnicoRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }




}
