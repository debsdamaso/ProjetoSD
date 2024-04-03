package com.safedose.apimedicamentos.medicamento;

import java.time.LocalDate;


public record DadosDetalhamentoMedicamento(Long id, String nome, Via via, Laboratorio laboratorio, LocalDate validade,
		String lote, int quantidade) {

	public DadosDetalhamentoMedicamento(Medicamento medicamento) {
		this(medicamento.getId(), medicamento.getNome(), medicamento.getVia(), medicamento.getLaboratorio(), medicamento.getValidade(),
				medicamento.getLote(), medicamento.getQuantidade());
	}
}
