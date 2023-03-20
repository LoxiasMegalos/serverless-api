package redes.serverless.serverless.domain.resources.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import redes.serverless.serverless.domain.resources.entity.Resources;
import redes.serverless.serverless.domain.resources.entity.ServicoConta;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InformacaoServicoDTO{

        Long id;
        String user_account;
        String service_name;
        List<RecursosServicoDTO> recursos = new ArrayList<>();

        public InformacaoServicoDTO(ServicoConta servicoConta) {
                id = servicoConta.getId();
                user_account = servicoConta.getUserAccount();
                service_name = servicoConta.getServiceName();
                recursos = servicoConta.getResources().stream().map(RecursosServicoDTO::new).toList();
        }


        public InformacaoServicoDTO(ServicoConta s, Resources r) {
                id = s.getId();
                user_account = s.getUserAccount();
                service_name = s.getServiceName();
                recursos.add(new RecursosServicoDTO(r));
        }
}
