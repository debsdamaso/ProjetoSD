package com.safedose.apimedicamentos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.safedose.apimedicamentos.model.Medicamento; 

public interface MedicamentoRepository extends JpaRepository<Medicamento, Long> {

}
