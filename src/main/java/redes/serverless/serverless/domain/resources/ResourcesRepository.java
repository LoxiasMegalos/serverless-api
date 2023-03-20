package redes.serverless.serverless.domain.resources;

import org.springframework.data.jpa.repository.JpaRepository;
import redes.serverless.serverless.domain.resources.entity.Resources;

public interface ResourcesRepository extends JpaRepository<Resources, Long> {

    Resources  findByServiceAccountId(Long id);
}
