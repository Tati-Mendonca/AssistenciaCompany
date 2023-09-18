package br.com.fiap.asssistenciaco.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ServicoAtualizacaoDTO {

    @NotNull(message = "Id é obrigatório")
    private Integer id;

    @NotBlank(message = "Preencha o campo descrição")
    @Size(max = 50, message = "Descrição tem o tamanho máximo de 50 caracteres")
    private String descricao;

    @DecimalMin(value = "20.00", message = "Valor deve ser maior ou igual a 20 reais")
    @Digits(fraction = 2, integer = 4, message = "Valor inválido para casas decimais")
    private BigDecimal valor;
}
