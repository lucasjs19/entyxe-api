package com.entyxe.service;

import com.entyxe.domain.entity.Cliente;
import com.entyxe.domain.entity.Endereco;
import com.entyxe.dto.request.EnderecoRequest;
import com.entyxe.dto.response.EnderecoResponse;
import com.entyxe.mapper.EnderecoMapper;
import com.entyxe.repository.ClienteRepository;
import com.entyxe.repository.EnderecoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EnderecoService {

    private final EnderecoRepository enderecoRepository;
    private final ClienteRepository clienteRepository;

    private void desmarcarEnderecoPrincipal(Long clienteId) {
        List<Endereco> enderecos = enderecoRepository.findByClienteIdAndAtivoTrue(clienteId);
        enderecos.forEach(e -> e.setPrincipal(false));
    }

    @Transactional
    public EnderecoResponse criar(Long clienteId, EnderecoRequest request) {
        System.out.println("ID DO CLIENTE: " + clienteId);
        Cliente cliente = clienteRepository.findByIdAndAtivoTrue(clienteId)
                .orElseThrow(() -> new RuntimeException("Cliente nao encontrado"));

        if (Boolean.TRUE.equals(request.getPrincipal())){
            desmarcarEnderecoPrincipal(clienteId);
        }

        Endereco endereco = EnderecoMapper.toEntity(request, cliente);
        Endereco salvo = enderecoRepository.save(endereco);

        return EnderecoMapper.toResponse(salvo);
    }

    public List<EnderecoResponse> listar(Long clienteId) {
        return enderecoRepository.findByClienteIdAndAtivoTrue(clienteId)
                .stream()
                .map(EnderecoMapper::toResponse)
                .toList();
    }

    @Transactional
    public void remover(Long clienteId, Long enderecoId) {

        Endereco endereco = enderecoRepository
                .findByIdAndClienteIdAndAtivoTrue(enderecoId, clienteId)
                .orElseThrow(() -> new RuntimeException("Endereço não encontrado"));

        endereco.setAtivo(false);
    }

}
