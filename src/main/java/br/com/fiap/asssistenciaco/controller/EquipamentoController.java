package br.com.fiap.asssistenciaco.controller;

import br.com.fiap.asssistenciaco.enums.TipoEquipamentoEnum;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/equipamentos")
@CrossOrigin(origins = "*", maxAge = 3600)
public class EquipamentoController {

    @GetMapping("/tipos")
    @Tag(name = "/tipos", description = "Recurso para gerênciar os tipos de serviços disponíbilizados pela assistência técnica")
    public List<TipoEquipamentoEnum> getTiposEquipamento() {
        return List.of(TipoEquipamentoEnum.values());
    }

}
