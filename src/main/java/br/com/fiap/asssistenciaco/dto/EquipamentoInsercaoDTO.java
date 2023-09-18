package br.com.fiap.asssistenciaco.dto;

import br.com.fiap.asssistenciaco.enums.TipoEquipamentoEnum;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EquipamentoInsercaoDTO {

    private String modelo;

    @NotNull(message = "Tipo de equipamento é obrigatório")
    private TipoEquipamentoEnum tipo;

    public String getMarca() {
        return tipo.name();
    }

}
