package redes.serverless.serverless.domain.resources.model;

public record NovoServicoDTO(
        String conta,
        String servico,
        Integer quantidadeMensagens
) {
}
