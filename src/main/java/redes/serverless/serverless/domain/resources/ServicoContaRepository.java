package redes.serverless.serverless.domain.resources;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import redes.serverless.serverless.domain.resources.entity.ServicoConta;


import java.util.List;

public interface ServicoContaRepository extends JpaRepository<ServicoConta, Long> {
    ServicoConta findByUserAccountAndServiceName(String conta, String servico);

    List<ServicoConta> findByUserAccount(String conta);

//    @Query("SELECT * FROM ServicoConta")
//    List<ServicoConta> queryListaContaServico();
}
