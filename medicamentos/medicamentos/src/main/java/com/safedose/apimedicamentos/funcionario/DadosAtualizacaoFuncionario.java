package com.safedose.apimedicamentos.funcionario;

import com.safedose.apimedicamentos.endereco.DadosEndereco;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoFuncionario(
		@NotNull
		Long id,
		@NotBlank
		String nome,
		@NotBlank
		@Email
		String email,
		@NotNull
		@Valid
		DadosEndereco endereco
		) {

}
