package redes.serverless.serverless.domain.resources.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import redes.serverless.serverless.domain.resources.entity.Resources;
import redes.serverless.serverless.domain.resources.entity.ServicoConta;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RecursosServicoDTO {
    Long id;
    Integer alocatedCores;
    Timestamp startDate;
    Timestamp endDate;
    Integer quantidadeMensagens;

    public RecursosServicoDTO(Resources resources) {
        id = resources.getId();
        alocatedCores = resources.getAllocatedCores();
        startDate = resources.getStartDate();
        endDate = resources.getEndDate();
        quantidadeMensagens = resources.getQuantidadeMensagens();
    }


}
