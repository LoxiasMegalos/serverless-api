package redes.serverless.serverless.domain.resources.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import redes.serverless.serverless.domain.resources.model.AlocatedRewsourcesDTO;
import redes.serverless.serverless.domain.resources.model.InformacaoServicoDTO;
import redes.serverless.serverless.domain.resources.model.NovoServicoDTO;
import redes.serverless.serverless.domain.resources.service.ResourcesService;

import java.util.List;

@RestController
@RequestMapping("/resources")
public class ResourcesController {

    @Autowired
    private ResourcesService service;

    @PostMapping()
    public AlocatedRewsourcesDTO startService(@RequestBody @Valid NovoServicoDTO dto){
        return service.alocaRecursos(dto);
    }

    @GetMapping()
    public List<InformacaoServicoDTO> getAll(){
        return service.getAll();
    }

    @GetMapping("/account/{acc}")
    public List<InformacaoServicoDTO> getAllByAccount(@PathVariable String acc){
        return service.getAllFromAccount(acc);
    }

    @GetMapping("/active/{acc}")
    public List<InformacaoServicoDTO> getAllByAccountActive(@PathVariable String acc){
        return service.getAllFromAccountActive(acc);
    }
}
