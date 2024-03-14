package com.safedose.apimedicamentos.service;

import com.safedose.apimedicamentos.model.Medicamento;
import com.safedose.apimedicamentos.repository.MedicamentoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MedicamentoService {

    private final MedicamentoRepository medicamentoRepository;

    @Autowired
    public MedicamentoService(MedicamentoRepository medicamentoRepository) {
        this.medicamentoRepository = medicamentoRepository;
    }

    // Método para salvar um novo medicamento
    public Medicamento salvar(Medicamento medicamento) {
        return medicamentoRepository.save(medicamento);
    }

    // Método para listar todos os medicamentos
    public List<Medicamento> listarTodos() {
        return medicamentoRepository.findAll();
    }

    // Método para obter um medicamento por ID
    public Optional<Medicamento> obterPorId(Long id) {
        return medicamentoRepository.findById(id);
    }

    // Método para atualizar um medicamento existente
    public Optional<Medicamento> atualizar(Long id, Medicamento medicamentoAtualizado) {
        Optional<Medicamento> medicamentoOptional = medicamentoRepository.findById(id);
        if (medicamentoOptional.isPresent()) {
            Medicamento medicamento = medicamentoOptional.get();
            medicamento.setNome(medicamentoAtualizado.getNome());
            medicamento.setFabricante(medicamentoAtualizado.getFabricante());
            medicamento.setQuantidade(medicamentoAtualizado.getQuantidade());
            return Optional.of(medicamentoRepository.save(medicamento));
        } else {
            return Optional.empty();
        }
    }

    // Método para deletar um medicamento
    public void deletar(Long id) {
        medicamentoRepository.deleteById(id);
    }
}

