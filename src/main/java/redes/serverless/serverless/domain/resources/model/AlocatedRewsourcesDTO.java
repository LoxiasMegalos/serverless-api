package redes.serverless.serverless.domain.resources.model;

public record AlocatedRewsourcesDTO(
        String account,
        String service,
        Integer allocatedCores,
        String message
) {
}
