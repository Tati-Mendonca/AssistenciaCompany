package br.com.fiap.asssistenciaco.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "TBL_ITEM_SERVICO")
public class ItemServico {

    @Id
    @Column(name = "ID_ITEM_SERVICO")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "ID_ORDEM_SERVICO")
    private OrdemServico ordemServico;

    @ManyToOne
    @JoinColumn(name = "ID_SERVICO")
    private Servico servico;

    @Column(name = "NR_VALOR")
    private BigDecimal valor;

}
