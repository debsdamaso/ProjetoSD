package com.safedose.apimedicamentos.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.safedose.apimedicamentos.medicamento.DadosAtualizacaoMedicamento;
import com.safedose.apimedicamentos.medicamento.DadosCadastroMedicamento;
import com.safedose.apimedicamentos.medicamento.DadosDetalhamentoMedicamento;
import com.safedose.apimedicamentos.medicamento.DadosListagemMedicamento;
import com.safedose.apimedicamentos.medicamento.Medicamento;
import com.safedose.apimedicamentos.medicamento.MedicamentoRepository;
import com.safedose.apimedicamentos.services.MedicamentoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("medicamentos")
@Tag(name = "Medicamentos", description = "EndPoints para controle de medicamentos")
public class MedicamentoController {

	@Autowired
	private MedicamentoRepository repository;
	@Autowired
	private MedicamentoService service;

	@PostMapping
	@Transactional
	@Operation(summary = "Cadastre um medicamento",
	description ="Cadastre um medicamento", 
	tags = {"Medicamentos"},
	responses = {
			@io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "200",content = @Content(
					mediaType = "application/json")),
			@io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "400",content = @Content),
			@io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "401",content = @Content),
			@io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "404",content = @Content),
			@io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "500",content = @Content),
	})
	public ResponseEntity<DadosDetalhamentoMedicamento> cadastrar(@RequestBody @Valid DadosCadastroMedicamento dados,
			UriComponentsBuilder uriBuilder) {
		var medicamentoExistente = repository.findByNome(dados.Getnome());
		if (medicamentoExistente != null) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
		var medicamento = new Medicamento(dados);

		repository.save(medicamento);

		var uri = uriBuilder.path("/medicamentos/{id}").buildAndExpand(medicamento.getId()).toUri();

		return ResponseEntity.created(uri).body(new DadosDetalhamentoMedicamento(medicamento));

	}

	@GetMapping
	@Operation(summary = "Busque todos os remédios cadastrados",
	description ="Busque todos os remédios cadastrados", 
	tags = {"Remédios"},
	
	responses = {
					@io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "200",content = @Content(
							mediaType = "application/json",
							schema = @Schema(implementation = DadosListagemMedicamento.class),
							examples = @ExampleObject())),
			@io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "400",content = @Content),
			@io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "401",content = @Content),
			@io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "404",content = @Content),
			@io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "500",content = @Content),
	})
	public List<DadosListagemMedicamento> listar() {
		return repository.findAllByAtivoTrue().stream().map(DadosListagemMedicamento::new).toList();
	}

	@GetMapping("/{id}")
	@Operation(summary = "Busque por um detalhamento de um remédio por id",
	description ="Busque por um detalhamento de um remédio por id", 
	tags = {"Remédios"},
	responses = {
			@io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "200",content = @Content(
					mediaType = "application/json",
					schema = @Schema(implementation = DadosDetalhamentoMedicamento.class),
					examples = @ExampleObject())),
			@io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "204",content = @Content),
			@io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "400",content = @Content),
			@io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "401",content = @Content),
			@io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "404",content = @Content),
			@io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "500",content = @Content),
	})
	public ResponseEntity<DadosDetalhamentoMedicamento> buscarPorId(@PathVariable Long id) {
		var medicamento = repository.getReferenceById(id);
		return ResponseEntity.ok(new DadosDetalhamentoMedicamento(medicamento));
	}

	@GetMapping("/nome/{nome}")
	@Operation(summary = "Busque por um detalhamento de um medicamento por nome",
	description ="Busque por um detalhamento de um medicamento por nome", 
	tags = {"Medicamentos"},
			responses = {
					@io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "200",content = @Content(
							mediaType = "application/json",
							schema = @Schema(implementation = DadosDetalhamentoMedicamento.class),
							examples = @ExampleObject())),
			@io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "204",content = @Content),
			@io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "400",content = @Content),
			@io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "401",content = @Content),
			@io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "404",content = @Content),
			@io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "500",content = @Content),
	})
	public ResponseEntity<DadosDetalhamentoMedicamento> buscarPorNome(@PathVariable String nome) {
		var medicamento = repository.findByNome(nome);
		return ResponseEntity.ok(new DadosDetalhamentoMedicamento(medicamento));
	}

	@PutMapping
	@Transactional
	@Operation(summary = "Atualize o cadastro de algum medicamento",
	description ="Atualize o cadastro de algum medicamento", 
	tags = {"Medicamentos"},
			responses = {
					@io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "200",content = @Content(
							mediaType = "application/json")),
			@io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "400",content = @Content),
			@io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "401",content = @Content),
			@io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "404",content = @Content),
			@io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "500",content = @Content),
	})
	public ResponseEntity<DadosDetalhamentoMedicamento> atualizar(@RequestBody @Valid DadosAtualizacaoMedicamento dados) {
		var medicamento = repository.getReferenceById(dados.id());
		medicamento.atualizarInformacoes(dados);
		return ResponseEntity.ok(new DadosDetalhamentoMedicamento(medicamento));
	}

	@PutMapping("/removequantidade/{id}")
	@Transactional
	@Operation(summary = "Remova um valor à quantidade em estoque",
	description ="Remova a quantidade em estoque", 
	tags = {"Medicamentos"},
	responses = {
			@io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "200",content = @Content),
			@io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "204",content = @Content),
			@io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "400",content = @Content),
			@io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "401",content = @Content),
			@io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "404",content = @Content),
			@io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "500",content = @Content),
	})
	public ResponseEntity<?> retiradaDeEstoque(@PathVariable Long id, @RequestBody Integer quantidade) {
		service.validacaoQuantidade(quantidade);
		service.removerQuantidade(id, quantidade.intValue());
		return ResponseEntity.noContent().build();
	}

	@PutMapping("/adicionaquantidade/{id}")
	@Transactional
	@Operation(summary = "Adicione um valor à quantidade em estoque",
	description ="Adicione um valor à quantidade em estoque", 
	tags = {"Medicamentos"},
	responses = {
			@io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "200",content = @Content),
			@io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "204",content = @Content),
			@io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "400",content = @Content),
			@io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "401",content = @Content),
			@io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "404",content = @Content),
			@io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "500",content = @Content),
	})
	public ResponseEntity<?> AdicionaEstoque(@PathVariable long id, @RequestBody Integer quantidade) {
		service.validacaoQuantidade(quantidade);
		service.adicionarQuantidade(id, quantidade.intValue());
		return ResponseEntity.noContent().build();

	}

	@PutMapping("/reativando/{id}")
	@Transactional
	@Operation(summary = "Reative o cadastro de um medicamento pelo Id",
	description ="Reative o cadastro de um medicamento pelo id", 
	tags = {"Medicamentos"},
	responses = {
			@io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "200",content = @Content),
			@io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "204",content = @Content),
			@io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "400",content = @Content),
			@io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "401",content = @Content),
			@io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "404",content = @Content),
			@io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "500",content = @Content),
	})
	public ResponseEntity<?> Reativar(@PathVariable Long id) {
		var medicamento = repository.findById(id);
		medicamento.get().setAtivo();
		repository.save(medicamento.get());
		return ResponseEntity.ok(medicamento.get());
	}

	@DeleteMapping("/inativando/{id}")
	@Transactional
	@Operation(summary = "Desative o cadastro de um medicamento pelo id",
	description ="Desative o cadastro de um medicamento pelo id", 
	tags = {"Medicamentos"},
	responses = {
			@io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "200",content = @Content),
			@io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "204",content = @Content),
			@io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "400",content = @Content),
			@io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "401",content = @Content),
			@io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "404",content = @Content),
			@io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "500",content = @Content),
	})
	public ResponseEntity<?> Inativar(@PathVariable Long id) {
		var medicamento = repository.getReferenceById(id);
		medicamento.setInativo();
		return ResponseEntity.noContent().build();

	}

	@DeleteMapping("/{id}")
	@Transactional
	@Operation(summary = "Exclua um medicamento",
	description ="Exclua um medicamento", 
	tags = {"Medicamentos"},
	responses = {
			@io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "204",content = @Content),
			@io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "400",content = @Content),
			@io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "401",content = @Content),
			@io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "404",content = @Content),
			@io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "500",content = @Content),
	})
	public ResponseEntity<?> Excluir(@PathVariable Long id) {
		repository.deleteById(id);
		return ResponseEntity.noContent().build();
	}

}