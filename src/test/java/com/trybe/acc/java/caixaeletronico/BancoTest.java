package com.trybe.acc.java.caixaeletronico;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.regex.Pattern;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Testes para a classe Banco")
class BancoTest {
  private Banco banco;
  private PessoaCliente cliente;

  public BancoTest() {
    banco = new Banco();
    cliente = banco.adicionarPessoaCliente("Alexiania Pereira", "842.074.410-77", "1234");
  }

  @Test
  @DisplayName("1 - Testa o gerador de número único para nova conta.")
  void gerarNumeroNovaContaTest() {
    String numeroConta = banco.gerarNumeroNovaConta();
    boolean numeroContaValido = Pattern.matches("\\d{10}", numeroConta);
    assertTrue(numeroContaValido);
  }

  @Test
  @DisplayName("2 - Testa o método adicionar pessoa cliente retorna o objeto pessoa cliente.")
  void adicionarPessoaClienteTest() {
    assertNotNull(cliente);
    assertEquals("842.074.410-77", cliente.getCpf());
  }

  @Test
  @DisplayName("2.1 - Testa o método adicionar conta.")
  void adicionarContaTest() {
    Conta conta = new Conta("Corrente", cliente, banco);
    banco.adicionarConta(conta);
    assertEquals(1, banco.getContas().size());
  }

  @Test
  @DisplayName("3 - Testa o método login da pessoa cliente retorna o objeto pessoa cliente corretamente.")
  void pessoaClienteLoginTest() {
    PessoaCliente clienteLogado = banco.pessoaClienteLogin("842.074.410-77", "1234");
    assertNotNull(clienteLogado);
    assertEquals("842.074.410-77", clienteLogado.getCpf());
    
    PessoaCliente clienteInvalido = banco.pessoaClienteLogin("992.074.410-77", "1234");
    assertNull(clienteInvalido);
  }

  @Test
  @DisplayName("4 - Testa se o método transferir fundos está transferindo corretamente.")
  void depositarTestTransferirFundosTestmostrarExtratoTest() {
    PessoaCliente clienteLogado = this.banco.pessoaClienteLogin("842.074.410-77", "1234");
    clienteLogado.adicionarConta(new Conta("Corrente", clienteLogado, this.banco));
    clienteLogado.adicionarConta(new Conta("Poupança", clienteLogado, this.banco));
    this.banco.depositar(clienteLogado, 0, 1000.0);
    this.banco.transferirFundos(clienteLogado, 0, 1, 300.0);

    ByteArrayOutputStream saidaConsole = new ByteArrayOutputStream();
    System.setOut(new PrintStream(saidaConsole));

    this.banco.mostrarExtrato(clienteLogado, 0);
    assertTrue(saidaConsole.toString().contains("Depósito"));
    assertTrue(saidaConsole.toString().contains("1000.0"));
    assertTrue(saidaConsole.toString().contains("Saque"));
    assertTrue(saidaConsole.toString().contains("300.0"));

    saidaConsole = new ByteArrayOutputStream();
    System.setOut(new PrintStream(saidaConsole));

    this.banco.mostrarExtrato(clienteLogado, 1);
    assertTrue(saidaConsole.toString().contains("Depósito"));
    assertTrue(saidaConsole.toString().contains("300.0"));
  }

  @Test
  @DisplayName("5 - Testa se o método sacar está funcionando corretamente.")
  void depositarTestSacarTestMostrarExtratoTest() {
    PessoaCliente clienteLogado = this.banco.pessoaClienteLogin("842.074.410-77", "1234");
    clienteLogado.adicionarConta(new Conta("Corrente", clienteLogado, this.banco));
    this.banco.depositar(clienteLogado, 0, 1000.0);
    this.banco.sacar(clienteLogado, 0, 300.0);

    ByteArrayOutputStream saidaConsole = new ByteArrayOutputStream();
    System.setOut(new PrintStream(saidaConsole));

    this.banco.mostrarExtrato(clienteLogado, 0);
    assertTrue(saidaConsole.toString().contains("Saque"));
    assertTrue(saidaConsole.toString().contains("300.0"));
  }

}
