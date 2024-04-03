package com.safedose.apimedicamentos.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safedose.apimedicamentos.medicamento.MedicamentoRepository;

@Service
public class MedicamentoService {

	@Autowired
	private MedicamentoRepository repository;
	
	public void validacaoQuantidade(Integer quantidade) {
		if (quantidade == null) {
			throw new RuntimeException("Parâmetro quantidade é obrigatório");
		}
	}
	
	public void adicionarQuantidade(Long id, int quantidade) {
		validacaoQuantidade(quantidade);
		var medicamento = repository.getReferenceById(id);
		medicamento.addQuantidade(quantidade);
		repository.save(medicamento);
	}
	
	public void removerQuantidade(Long id, int quantidade) {
		validacaoQuantidade(quantidade);
		var medicamento = repository.getReferenceById(id);
		medicamento.removeQuantidade(quantidade);
		repository.save(medicamento);
	}
}

