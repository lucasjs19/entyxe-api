package com.entyxe.controller;

import com.entyxe.domain.entity.Endereco;
import com.entyxe.dto.request.EnderecoRequest;
import com.entyxe.dto.response.EnderecoResponse;
import com.entyxe.mapper.EnderecoMapper;
import com.entyxe.service.EnderecoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(
        value = "/clientes/{clienteId}/enderecos",
        produces = "application/json"
)
@RequiredArgsConstructor
public class EnderecoController {
    private final EnderecoService enderecoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EnderecoResponse criar(
            @PathVariable Long clienteId,
            @RequestBody @Valid EnderecoRequest request
            ){
        return enderecoService.criar(clienteId, request);
    }

    @GetMapping
    public List<EnderecoResponse> listar(@PathVariable Long clienteId){
        return enderecoService.listar(clienteId);
    }

    @DeleteMapping("/{enderecoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(
            @PathVariable Long clienteId,
            @PathVariable Long enderecoId
    ) {
        enderecoService.remover(clienteId, enderecoId);
    }

}
