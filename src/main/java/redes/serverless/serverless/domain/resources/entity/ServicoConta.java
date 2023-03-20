package redes.serverless.serverless.domain.resources.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Table(name = "user_services_db")
@Entity(name = "user_services_db")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class ServicoConta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userAccount;

    private String serviceName;

    @OneToMany(mappedBy="serviceAccount")
    private List<Resources> resources;

    public ServicoConta(String conta, String servico) {
        this.userAccount = conta;
        this.serviceName = servico;
    }


    @Override
    public String toString() {

        return this.serviceName + this.userAccount + this.resources.toString();
    }
}
