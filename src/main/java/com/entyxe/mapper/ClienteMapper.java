package com.entyxe.mapper;

import com.entyxe.domain.entity.Cliente;
import com.entyxe.dto.request.ClienteRequest;
import com.entyxe.dto.response.ClienteResponse;

public class ClienteMapper {
    public static Cliente toEntity(ClienteRequest request){
        Cliente cliente = new Cliente();
        cliente.setNome(request.getNome());
        cliente.setTipoCliente(request.getTipoCliente());
        cliente.setDocumento(request.getDocumento());
        cliente.setEmail(request.getEmail());
        cliente.setAtivo(true);
        return cliente;
    }

    public static ClienteResponse toResponse(Cliente cliente){
        ClienteResponse response = new ClienteResponse();
        response.setId(cliente.getId());
        response.setNome(cliente.getNome());
        response.setEmail(cliente.getEmail());
        response.setTipo(cliente.getTipoCliente());
        response.setDocumento(cliente.getDocumento());
        response.setCriadoEm(cliente.getCriadoEm());
        return response;
    }

    public static void updateEntity(Cliente cliente, ClienteRequest request){
        cliente.setNome(request.getNome());
        cliente.setTipoCliente(request.getTipoCliente());
        cliente.setEmail(request.getEmail());
    }
}
