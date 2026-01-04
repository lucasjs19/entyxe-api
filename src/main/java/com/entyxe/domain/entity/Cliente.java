package com.entyxe.domain.entity;

import com.entyxe.domain.enums.TipoCliente;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

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
    @Column(nullable = false, name = "tipo_cliente")
    private TipoCliente tipoCliente;

    @Size(min = 11, max = 14)
    @Column(nullable = false, length = 14, unique = true, updatable = false)
    private String documento;

    @Column(nullable = false)
    private Boolean ativo = true;

    @Column(nullable = false, updatable = false)
    private LocalDateTime criadoEm;

    private LocalDateTime atualizadoEm;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Endereco> enderecos;

    @PrePersist
    void prePersist(){
        criadoEm = LocalDateTime.now();
    }

    @PreUpdate
    void preUpdate(){
        atualizadoEm = LocalDateTime.now();
    }
}
