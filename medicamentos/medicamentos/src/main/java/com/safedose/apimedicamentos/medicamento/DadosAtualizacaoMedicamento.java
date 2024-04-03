package com.safedose.apimedicamentos.medicamento;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoMedicamento(
		@NotNull
		Long id,

		String nome,
		
		Via via,
		
		Laboratorio laboratorio) {
	
	

}
