package br.com.fiap.asssistenciaco.dto;

import br.com.fiap.asssistenciaco.enums.TipoEquipamentoEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EquipamentoResponseDTO {

    private Integer id;
    private String numeroSerie;
    private String marca;
    private String modelo;
    private TipoEquipamentoEnum tipo;

}
