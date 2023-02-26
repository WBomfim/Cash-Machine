package com.trybe.acc.java.caixaeletronico;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.regex.Pattern;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Teste da classe Conta")
class ContaTest {
  private String tipoConta;
  private Conta conta;
  private PessoaCliente pessoaCliente;
  private Banco banco;

  public ContaTest() {
    this.tipoConta = "Corrente";
    this.pessoaCliente = new PessoaCliente("Alexiania Pereira", "842.074.410-77", "1234");
    this.banco = new Banco();
    this.conta = new Conta(tipoConta, pessoaCliente, banco);
    conta.adicionarTransacao(100.0, "Depósito");
    conta.adicionarTransacao(50.0, "Depósito");
    conta.adicionarTransacao(30.0, "Saque");
  }


  @Test
  @DisplayName("6 - Testa o construtor da classe conta.")
  void construtorTest() {
    assertEquals(tipoConta, conta.getTipoConta());
    assertEquals(pessoaCliente, conta.getPessoaCliente());
  }

  @Test
  @DisplayName("7 - Testa o método adicionar transação e retornar saldo da conta.")
  void adicionarTransacaoTestRetornarSaldoTest() {
    assertEquals(120.0, conta.retornarSaldo());
  }

  @Test
  @DisplayName("8 - Testa o método retornar resumo está retornando uma string com os valores corretamente.")
  void retornarResumoContaTest() {
    String idConta = conta.getIdConta();
    String tipoConta = conta.getTipoConta();
    String resumoConta = conta.retornarResumoConta();
    String resumoEsperado = "Conta: " + tipoConta + " " + idConta + " com saldo de: " + conta.retornarSaldo();
    assertEquals(resumoEsperado, resumoConta);
  }

  @Test
  @DisplayName("9 - Testa o método retornar extrato está imprimindo os valores corretamente.")
  void retornarExtratoTest() {
    ByteArrayOutputStream saidaConsole = new ByteArrayOutputStream();
    System.setOut(new PrintStream(saidaConsole));

    conta.retornarExtrato();
    String saida = saidaConsole.toString();
    
    assertTrue(saida.contains("Depósito"));
    assertTrue(saida.contains("100.0"));
    assertTrue(saida.contains("50.0"));
    assertTrue(saida.contains("Saque"));
    assertTrue(saida.contains("30.0"));
  }

  @Test
  @DisplayName("10 - Testa o método Getter do atributo idConta está retornando.")
  void getIdContaTest() {
    String idConta = conta.getIdConta();
    boolean idContaValido = Pattern.matches("\\d{10}", idConta);
    assertTrue(idContaValido);
  }

  @Test
  @DisplayName("11 - Testa o método método Getter do atributo pessoaCliente está retornando.")
  void getPessoaClienteTest() {
    PessoaCliente pessoaCliente = conta.getPessoaCliente();
    assertEquals(this.pessoaCliente, pessoaCliente);
  }

}
