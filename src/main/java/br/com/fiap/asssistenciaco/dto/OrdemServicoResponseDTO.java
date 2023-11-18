package br.com.fiap.asssistenciaco.dto;

import br.com.fiap.asssistenciaco.entity.Telefone;
import br.com.fiap.asssistenciaco.enums.PrioridadeExecucaoEnum;
import br.com.fiap.asssistenciaco.enums.StatusExecucaoEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
public class OrdemServicoResponseDTO {

    private Long id;
    private EquipamentoResponseDTO equipamento;
    private ClienteResponseDTO cliente;

    @JsonIgnore
    private LocalDateTime dataEntrada;

    @JsonIgnore
    private LocalDateTime dataPrevisao;

    private String documento;

    private Telefone telefone;

    private String defeito;
    private PrioridadeExecucaoEnum prioridade;
    private LocalDateTime dataSaida;
    private StatusExecucaoEnum status;
    private String observacoes;

    public LocalDate getEntrada() {
        return Objects.isNull(dataEntrada) ? null
                : dataEntrada.toLocalDate();
    }

    public LocalDate getPrevisao() {
        return Objects.isNull(dataPrevisao) ? null
                : dataPrevisao.toLocalDate();
    }

}