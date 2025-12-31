package com.entyxe.domain.entity;

import com.entyxe.domain.enums.TipoCliente;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "clientes")
@Getter
@Setter
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String email;

    @Enumerated
    @Column(nullable = false)
    private TipoCliente tipoCliente;

    @Column(nullable = false)
    private Boolean ativo = true;

    @Column(nullable = false, updatable = false)
    private LocalDateTime criadoEm;

    private LocalDateTime atualizadoEm;

    @PrePersist
    void prePersist(){
        criadoEm = LocalDateTime.now();
    }

    @PreUpdate
    void preUpdate(){
        atualizadoEm = LocalDateTime.now();
    }
}
