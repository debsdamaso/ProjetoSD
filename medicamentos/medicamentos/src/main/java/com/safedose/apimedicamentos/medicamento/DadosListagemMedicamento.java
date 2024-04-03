package com.safedose.apimedicamentos.medicamento;

public record DadosListagemMedicamento(Long id, String nome, Laboratorio laboratorio, Via via , int quantidade) {
	
	public DadosListagemMedicamento(Medicamento medicamento) {
		this(medicamento.getId(),medicamento.getNome(), medicamento.getLaboratorio(), medicamento.getVia(), medicamento.getQuantidade());
	}

}
