package com.safedose.apimedicamentos.usuario;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

	UserDetails findByLogin(String login);

	Optional<Usuario> findByCpf(String cpf);

	Object findByNome(String nome);
	
}