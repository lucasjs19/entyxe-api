package com.entyxe.dto.response;

import com.entyxe.domain.enums.TipoCliente;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ClienteResponse {
    private Long id;
    private String nome;
    private TipoCliente tipo;
    private String email;
    private LocalDateTime criadoEm;
}
