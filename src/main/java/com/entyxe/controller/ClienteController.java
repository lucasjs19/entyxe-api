package com.entyxe.controller;

import com.entyxe.domain.entity.Cliente;
import com.entyxe.dto.request.ClienteRequest;
import com.entyxe.dto.response.ClienteResponse;
import com.entyxe.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(
        value = "/clientes",
        produces = "application/json"
)
public class ClienteController {
    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    //CRIAR
    @PostMapping
    public ClienteResponse criar(@RequestBody @Valid ClienteRequest request){
        return clienteService.criar(request);
    }

    //LISTAR
    @GetMapping
    public Page<ClienteResponse> listar(Pageable pageable){
        return clienteService.listar(pageable);
    }

    //LISTAR POR ID
    @GetMapping("/{id}")
    public ClienteResponse getById(@PathVariable Long id){
        return clienteService.buscarPorId(id);
    }

    //UPDATE
    @PutMapping("/{id}")
    public ClienteResponse atualizar(@PathVariable Long id, @RequestBody @Valid ClienteRequest request){
        return clienteService.atualizar(id, request);
    }

    //DELETE (SOFT DELETE)
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id){
        clienteService.deletar(id);
    }


}
