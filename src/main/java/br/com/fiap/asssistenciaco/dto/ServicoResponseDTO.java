package br.com.fiap.asssistenciaco.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
public class ServicoResponseDTO {

    private Long id;
    private String descricao;
    private BigDecimal valor;

}
