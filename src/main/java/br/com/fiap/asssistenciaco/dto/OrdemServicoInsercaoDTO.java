package br.com.fiap.asssistenciaco.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
public class OrdemServicoInsercaoDTO {

    @Valid
    @NotNull(message = "Equipamento é obrigatório")
    private EquipamentoInsercaoDTO equipamento;

    @NotBlank(message = "Cliente é obrigatório")
    private String cliente;
    private LocalDate entrada;
    private LocalDate previsao;

    @NotBlank(message = "Defeito é obrigatório")
    private String defeito;

    private String observacoes;

    @JsonIgnore
    public LocalDateTime getDataEntrada() {
        return Objects.isNull(entrada) ? null
                : entrada.atStartOfDay();
    }

    @JsonIgnore
    public LocalDateTime getDataPrevisao() {
        return Objects.isNull(previsao) ? null
                : previsao.atStartOfDay();
    }

}
