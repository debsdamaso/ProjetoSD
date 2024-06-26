package com.safedose.apimedicamentos.usuario;

import org.hibernate.validator.constraints.br.CPF;

import com.safedose.apimedicamentos.endereco.DadosEndereco;
import com.safedose.apimedicamentos.services.CpfJaCadastrado;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record DadosCadastroUsuario(
		
		@NotBlank
		String nome,
		@NotBlank
		String login,	
		@NotBlank
		@Size(min=6, max=30)
		String senha,	
		@NotBlank
		@Email
		String email,
		@CPF
		@CpfJaCadastrado
		String cpf,
		@NotNull
		@Valid
		DadosEndereco endereco
		) {

}
