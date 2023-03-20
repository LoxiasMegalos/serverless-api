package redes.serverless.serverless.domain.resources.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Table(name = "recursos_alocados_db")
@Entity(name = "recursos_alocados_db")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Resources {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer allocatedCores;

    private Integer quantidadeMensagens;

    @Column(name = "start_date")
    private java.sql.Timestamp startDate;

    @Column(name = "end_date")
    private java.sql.Timestamp endDate;

    @ManyToOne
    @JoinColumn(name = "serviceAccountId", nullable = false)
    private ServicoConta serviceAccount;


    public Resources(int i, int quantidadeMensagens,Instant instant, Instant after, ServicoConta sc) {
        this.allocatedCores = i;
        this.startDate = Timestamp.from(instant);
        this.endDate = Timestamp.from(after);
        this.quantidadeMensagens = quantidadeMensagens;
        this.serviceAccount = sc;
    }

    public Resources(Long id, int i, int quantidadeMensagens, Timestamp start, Instant after, ServicoConta sc) {
        this.id = id;
        this.allocatedCores = i;
        this.startDate = start;
        this.endDate = Timestamp.from(after);
        this.serviceAccount = sc;
        this.quantidadeMensagens = quantidadeMensagens;
    }

}
