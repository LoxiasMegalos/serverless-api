package redes.serverless.serverless.domain.resources.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redes.serverless.serverless.domain.resources.ResourcesRepository;
import redes.serverless.serverless.domain.resources.ServicoContaRepository;
import redes.serverless.serverless.domain.resources.entity.Resources;
import redes.serverless.serverless.domain.resources.entity.ServicoConta;
import redes.serverless.serverless.domain.resources.model.AlocatedRewsourcesDTO;
import redes.serverless.serverless.domain.resources.model.InformacaoServicoDTO;
import redes.serverless.serverless.domain.resources.model.NovoServicoDTO;
import redes.serverless.serverless.domain.resources.model.RecursosServicoDTO;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ResourcesService {

    @Autowired
    private ResourcesRepository resourceRepository;

    @Autowired
    private ServicoContaRepository serviceRepository;

    @Autowired
    private final ModelMapper modelMapper;

    public AlocatedRewsourcesDTO alocaRecursos(NovoServicoDTO dto) {
        ServicoConta sc = serviceRepository.findByUserAccountAndServiceName(dto.conta(), dto.servico());
        if (sc == null) {
            serviceRepository.save(new ServicoConta(dto.conta(), dto.servico()));
        }
        sc = serviceRepository.findByUserAccountAndServiceName(dto.conta(), dto.servico());

        var tempo = calculaTempo(dto.quantidadeMensagens());
        Instant instant = Instant.now();
        Instant after = Instant.now().plus(tempo, ChronoUnit.SECONDS);

        var coresAlocados = calculaCores(dto.quantidadeMensagens());

        if (sc.getResources() != null) {
            for (Resources r : sc.getResources()) {
                if (r.getEndDate().after(Timestamp.from(instant))) {
                    var mensagens = r.getQuantidadeMensagens() + dto.quantidadeMensagens();
                    var cores = r.getAllocatedCores() + coresAlocados;
                    var start = r.getStartDate();
                    resourceRepository.save(new Resources(r.getId(), cores, mensagens, start, after, sc));
                    return new AlocatedRewsourcesDTO(sc.getUserAccount(), sc.getServiceName(),
                            coresAlocados, "Serviço atualizado");
                }
            }
        }

        resourceRepository.save(new Resources(coresAlocados, dto.quantidadeMensagens(), instant, after, sc));
        return new AlocatedRewsourcesDTO(sc.getUserAccount(), sc.getServiceName(),
                coresAlocados, "Serviço criado");
    }

    private Integer calculaCores(Integer quantidadeMensagens) {
        if (quantidadeMensagens < 1000) {
            return 1;
        }
        var coresAlocados = 0;
        do {
            coresAlocados += 1;
            quantidadeMensagens = quantidadeMensagens - 1000;
        } while (quantidadeMensagens > 0);
        return coresAlocados;
    }

    private Integer calculaTempo(Integer quantidadeMensagens) {
        if (quantidadeMensagens < 1000) {
            return 60;
        }
        int tempo = 60;
        do {
            tempo += 30;
            quantidadeMensagens -= 1000;
        } while (quantidadeMensagens > 1000);
        return tempo;
    }

    public List<InformacaoServicoDTO> getAll() {
        return serviceRepository.findAll().stream().map(InformacaoServicoDTO::new).toList();
    }

    public List<InformacaoServicoDTO> getAllFromAccount(String conta){
        return serviceRepository.findByUserAccount(conta).stream().map(InformacaoServicoDTO::new).toList();
    }

    public List<InformacaoServicoDTO> getAllFromAccountActive(String conta) {

        List<InformacaoServicoDTO> recursos = new ArrayList<>();
        Instant instant = Instant.now();
        var services =  serviceRepository
                .findByUserAccount(conta);


        for(ServicoConta s : services){
            for(Resources r : s.getResources()){
                if(r.getEndDate().after(Timestamp.from(instant))){
                    recursos.add(new InformacaoServicoDTO(s, r));
                }
            }
        }

        return recursos;

    }
}
