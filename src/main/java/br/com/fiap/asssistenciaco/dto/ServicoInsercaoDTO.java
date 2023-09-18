package br.com.fiap.asssistenciaco.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ServicoInsercaoDTO {

    @NotBlank(message = "Preencha o campo descrição")
    @Size(max = 50, message = "Descrição tem o tamanho máximo de 50 caracteres")
    private  String descricao;

    @DecimalMin(value = "20.00", message = "Valor deve ser maior ou igual a 20 reais")
    @Digits(fraction = 2, integer = 4, message = "Valor inválido para casas decimais")
    private BigDecimal valor;

}
