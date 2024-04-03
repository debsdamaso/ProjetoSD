package com.safedose.apimedicamentos.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.safedose.apimedicamentos.usuario.Usuario;
import com.safedose.apimedicamentos.usuario.UsuarioRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;



public class ValidationCPFUserExists implements ConstraintValidator<CpfJaCadastrado, String>{

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public boolean isValid(String cpf, ConstraintValidatorContext context) {
		if (cpf == null) {
			return true;
		}
		Optional<Usuario> usuarioExistente = usuarioRepository.findByCpf(cpf);
		return !usuarioExistente.isPresent();
		
	}
}
