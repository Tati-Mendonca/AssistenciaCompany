package br.com.fiap.asssistenciaco.entity;

import jakarta.persistence.*;
//import jakarta.validation.constraints.DecimalMin;
//import jakarta.validation.constraints.Digits;
//import jakarta.validation.constraints.NotBlank;
//import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "TBL_SERVICO")
public class Servico {

    @Id
    @Column(name = "ID_SERVICO")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

//    @NotBlank(message = "Descrição é obrigatória")
//    @Size(max = 80, message = "Descrição tem tamanho máximo de 50 caracteres")
    @Column(name = "TX_DESCRICAO")
    private String descricao;

//    @DecimalMin(value = "20.00", message = "Valor deve ser maior ou igual a 20 reais")
//    @Digits(fraction = 2, integer = 4, message = "Valor inválido para casas decimais")
    @Column(name = "NR_VALOR")
    private BigDecimal valor;

}
