package br.com.fiap.asssistenciaco.entity;


import br.com.fiap.asssistenciaco.enums.TipoTelefoneEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class Telefone {

    @Column(name = "TX_AREA_FONE")
    private String area;

    @Column(name = "TX_FONE")
    private String numero;

    @Column(name = "TX_TIPO_FONE")
    @Enumerated(EnumType.STRING)
    private TipoTelefoneEnum tipo;

}
