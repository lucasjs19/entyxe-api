package com.entyxe.dto.request;

import com.entyxe.domain.enums.TipoCliente;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteRequest {
    @NotBlank
    private String nome;

    @NotNull
    private TipoCliente tipoCliente;

    @NotBlank
    private String email;
}
