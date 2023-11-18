package br.com.fiap.asssistenciaco.dto;

import br.com.fiap.asssistenciaco.entity.Telefone;
import br.com.fiap.asssistenciaco.enums.TipoDocumentoEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteResponseDTO {

    private Integer id;
    private TipoDocumentoEnum tipoDocumento;
    private String documento;
    private Telefone telefone;
 //   private String telefone;
    private String nome;

}
