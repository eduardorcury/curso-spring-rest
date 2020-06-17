package com.eduardorib.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eduardorib.domain.exception.NegocioException;
import com.eduardorib.domain.model.Cliente;
import com.eduardorib.domain.repository.ClienteRepository;

@Service
public class CadastroClienteService {
	
	@Autowired
	private ClienteRepository repo;
	
	public Cliente salvar(Cliente cliente) {
		
		Cliente clienteExistente = repo.findByEmail(cliente.getEmail());
		
		if (clienteExistente != null && !clienteExistente.equals(cliente)) {
			throw new NegocioException("Email já existente.");
		}
		
		return repo.save(cliente);
	}
	
	public void excluir(Long clienteId) {
		repo.deleteById(clienteId);
	}
	

}
