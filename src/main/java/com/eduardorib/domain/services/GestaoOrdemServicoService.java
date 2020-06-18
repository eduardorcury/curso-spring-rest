package com.eduardorib.domain.services;

import java.time.OffsetDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eduardorib.domain.exception.NegocioException;
import com.eduardorib.domain.model.Cliente;
import com.eduardorib.domain.model.OrdemServico;
import com.eduardorib.domain.model.StatusOrdemServico;
import com.eduardorib.domain.repository.ClienteRepository;
import com.eduardorib.domain.repository.OrdemServicoRepository;

@Service
public class GestaoOrdemServicoService {
	
	@Autowired
	private OrdemServicoRepository repo;
	
	@Autowired
	private ClienteRepository clienteRepo;
	
	public OrdemServico criar (OrdemServico ordemServico) {
		
		Cliente cliente = clienteRepo.findById(ordemServico.getCliente().getId())
				.orElseThrow(() -> new NegocioException("Cliente não encontrado"));
		
		ordemServico.setCliente(cliente);
		ordemServico.setStatus(StatusOrdemServico.ABERTA);
		ordemServico.setDataAbertura(OffsetDateTime.now());
		
		return repo.save(ordemServico);
		
	}

}
