package com.entyxe.repository;

import com.entyxe.domain.entity.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

    List<Endereco> findByClienteIdAndAtivoTrue(Long clienteId);

    Optional<Endereco> findByIdAndClienteIdAndAtivoTrue(Long id, Long clienteId);
}
