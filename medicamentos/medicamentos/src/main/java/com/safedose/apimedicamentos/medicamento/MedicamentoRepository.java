package com.safedose.apimedicamentos.medicamento;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MedicamentoRepository extends JpaRepository<Medicamento, Long>{
    List<Medicamento> findAllByAtivoTrue();

	@Query
	Medicamento findByNome(@Param("name") String name);

}