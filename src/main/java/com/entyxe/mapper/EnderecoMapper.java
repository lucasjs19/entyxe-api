package com.entyxe.mapper;

import com.entyxe.domain.entity.Cliente;
import com.entyxe.domain.entity.Endereco;
import com.entyxe.dto.request.EnderecoRequest;
import com.entyxe.dto.response.EnderecoResponse;

public class EnderecoMapper {
    public static Endereco toEntity(EnderecoRequest request, Cliente cliente){
        Endereco endereco = new Endereco();
        endereco.setLogradouro(request.getLogradouro());
        endereco.setNumero(request.getNumero());
        endereco.setComplemento(request.getComplemento());
        endereco.setBairro(request.getBairro());
        endereco.setCidade(request.getCidade());
        endereco.setEstado(request.getEstado());
        endereco.setCep(request.getCep());
        endereco.setPrincipal(Boolean.TRUE.equals(request.getPrincipal()));
        endereco.setCliente(cliente);
        return endereco;
    }

    public static EnderecoResponse toResponse(Endereco endereco){
        EnderecoResponse response = new EnderecoResponse();
        response.setId(endereco.getId());
        response.setLogradouro(endereco.getLogradouro());
        response.setNumero(endereco.getNumero());
        response.setComplemento(endereco.getComplemento());
        response.setBairro(endereco.getBairro());
        response.setCidade(endereco.getCidade());
        response.setEstado(endereco.getEstado());
        response.setCep(endereco.getCep());
        response.setPrincipal(endereco.getPrincipal());
        return response;
    }
}
