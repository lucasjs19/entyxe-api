package com.entyxe.service;

import com.entyxe.domain.entity.Cliente;
import com.entyxe.dto.request.ClienteRequest;
import com.entyxe.dto.response.ClienteResponse;
import com.entyxe.exception.ClienteNotFoundException;
import com.entyxe.mapper.ClienteMapper;
import com.entyxe.repository.ClienteRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {
    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    //CRIAR
    public ClienteResponse criar(ClienteRequest request){
        Cliente cliente = ClienteMapper.toEntity(request);
        Cliente salvo = clienteRepository.save(cliente);
        return ClienteMapper.toResponse(salvo);
    }

    //LISTAR TODOS
    public Page<ClienteResponse> listar(Pageable pageable){
        return clienteRepository.findByAtivoTrue(pageable)
                .map(ClienteMapper::toResponse);
    }

    //LISTAR POR ID
    public ClienteResponse buscarPorId(Long id){
        Cliente cliente = clienteRepository.findByIdAndAtivoTrue(id)
                .orElseThrow(() -> new ClienteNotFoundException(id));
        return ClienteMapper.toResponse(cliente);
    }

    //ATUALIZAR
    public ClienteResponse atualizar(Long id, ClienteRequest request){
        Cliente cliente = clienteRepository.findByIdAndAtivoTrue(id)
                .orElseThrow(() -> new ClienteNotFoundException(id));
        ClienteMapper.updateEntity(cliente, request);
        return ClienteMapper.toResponse(clienteRepository.save(cliente));
    }

    //SOFT DELETE
    @Transactional
    public void deletar(Long id){
        Cliente cliente = clienteRepository.findByIdAndAtivoTrue(id)
                .orElseThrow(() -> new ClienteNotFoundException(id));
        cliente.setAtivo(false);
        //clienteRepository.save(cliente);
    }
}
