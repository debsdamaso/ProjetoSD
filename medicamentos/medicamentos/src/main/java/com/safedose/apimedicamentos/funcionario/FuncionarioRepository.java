package com.safedose.apimedicamentos.funcionario;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long>{

	@Query
	Funcionario findByNome(@Param("name") String name);

	// Método para salvar uma lista de funcionários
    @Override
    <S extends Funcionario> List<S> saveAll(Iterable<S> funcionarios);



}
