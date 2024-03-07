package com.safedose.apimedicamentos.controller;

import com.safedose.apimedicamentos.model.Medicamento;
import com.safedose.apimedicamentos.service.MedicamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/medicamentos")
public class MedicamentoController {

    private final MedicamentoService medicamentoService;

    @Autowired
    public MedicamentoController(MedicamentoService medicamentoService) {
        this.medicamentoService = medicamentoService;
    }

    // Método para criar um novo medicamento
    @PostMapping
    public ResponseEntity<Medicamento> criarMedicamento(@RequestBody Medicamento medicamento) {
        Medicamento medicamentoSalvo = medicamentoService.salvar(medicamento);
        return new ResponseEntity<>(medicamentoSalvo, HttpStatus.CREATED);
    }

    // Método para listar todos os medicamentos
    @GetMapping
    public ResponseEntity<List<Medicamento>> listarMedicamentos() {
        List<Medicamento> medicamentos = medicamentoService.listarTodos();
        return new ResponseEntity<>(medicamentos, HttpStatus.OK);
    }

    // Método para obter detalhes de um medicamento específico
    @GetMapping("/{id}")
    public ResponseEntity<Medicamento> obterMedicamento(@PathVariable Long id) {
        Optional<Medicamento> medicamento = medicamentoService.obterPorId(id);
        return medicamento.map(m -> new ResponseEntity<>(m, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Método para atualizar um medicamento existente
    @PutMapping("/{id}")
    public ResponseEntity<Medicamento> atualizarMedicamento(@PathVariable Long id, @RequestBody Medicamento medicamentoAtualizado) {
        Optional<Medicamento> medicamento = medicamentoService.atualizar(id, medicamentoAtualizado);
        return medicamento.map(m -> new ResponseEntity<>(m, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Método para excluir um medicamento
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarMedicamento(@PathVariable Long id) {
        medicamentoService.deletar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
