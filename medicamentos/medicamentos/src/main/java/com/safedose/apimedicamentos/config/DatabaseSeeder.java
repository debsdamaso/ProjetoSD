package com.safedose.apimedicamentos.config;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.safedose.apimedicamentos.medicamento.Medicamento;
import com.safedose.apimedicamentos.funcionario.Funcionario;
import com.safedose.apimedicamentos.usuario.Usuario;
import com.safedose.apimedicamentos.medicamento.MedicamentoRepository;
import com.safedose.apimedicamentos.funcionario.FuncionarioRepository;
import com.safedose.apimedicamentos.usuario.UsuarioRepository;

@Configuration
public class DatabaseSeeder implements CommandLineRunner {

    @Autowired
    MedicamentoRepository medicamentoRepository;

    @Autowired
    FuncionarioRepository funcionarioRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    public void run(String... args) throws Exception {
        
        Medicamento medicamento = Medicamento.builder()
           .nome("Paracetamol")
           .validade(LocalDate.of(2024, 12, 31))
           .ativo(true)
           .quantidade(10)
           .build();
        medicamentoRepository.save(medicamento);

        Funcionario funcionario = Funcionario.builder()
           .nome("Debora Damaso")
           .email("deboradamaso16@gmail.com")
           .cpf("12345678910")
           .ativo(true)
           .build();
        funcionarioRepository.save(funcionario);

        Usuario usuario = Usuario.builder()
           .login("DebsDamaso")
           .senha("senha123")
           .email("deboradamaso16@gmail.com")
           .cpf("12345678910")
           .ativo(true)
           .build();
        usuarioRepository.save(usuario);

    }
}