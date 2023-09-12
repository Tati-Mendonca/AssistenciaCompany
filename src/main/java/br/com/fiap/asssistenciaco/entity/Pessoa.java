package br.com.fiap.asssistenciaco.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public abstract class Pessoa {

    @Column(name = "TX_NOME")
    protected String nome;

    @Embedded
    protected Telefone telefone;

    @Column(name = "TX_EMAIL")
    protected String email;

}
