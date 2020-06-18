package com.eduardorib.domain.services;

import java.time.OffsetDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eduardorib.api.model.Comentario;
import com.eduardorib.domain.exception.EntidadeNaoEncontradaException;
import com.eduardorib.domain.exception.NegocioException;
import com.eduardorib.domain.model.Cliente;
import com.eduardorib.domain.model.OrdemServico;
import com.eduardorib.domain.model.StatusOrdemServico;
import com.eduardorib.domain.repository.ClienteRepository;
import com.eduardorib.domain.repository.ComentarioRepository;
import com.eduardorib.domain.repository.OrdemServicoRepository;

@Service
public class GestaoOrdemServicoService {

	@Autowired
	private OrdemServicoRepository repo;

	@Autowired
	private ClienteRepository clienteRepo;
	
	@Autowired
	private ComentarioRepository comentarioRepo;

	public OrdemServico criar(OrdemServico ordemServico) {

		Cliente cliente = clienteRepo.findById(ordemServico.getCliente().getId())
				.orElseThrow(() -> new NegocioException("Cliente não encontrado"));

		ordemServico.setCliente(cliente);
		ordemServico.setStatus(StatusOrdemServico.ABERTA);
		ordemServico.setDataAbertura(OffsetDateTime.now());

		return repo.save(ordemServico);

	}
	
	public void finalizar(Long ordemServicoId) {
		
		OrdemServico ordemServico = buscar(ordemServicoId);
		ordemServico.finalizar();
		repo.save(ordemServico);
		
	}

	public Comentario adicionarComentario(Long ordemServicoId, String descricao) {

		OrdemServico ordemServico = buscar(ordemServicoId);

		Comentario comentario = new Comentario();
		comentario.setDataEnvio(OffsetDateTime.now());
		comentario.setDescricao(descricao);
		comentario.setOrdemServico(ordemServico);

		return comentarioRepo.save(comentario);
		
	}
	
	private OrdemServico buscar(Long ordemServicoId) {
		return repo.findById(ordemServicoId)
				.orElseThrow(() -> new EntidadeNaoEncontradaException("Ordem de serviço não enconstrada"));
	}

}
