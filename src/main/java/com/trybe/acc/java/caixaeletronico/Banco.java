package com.trybe.acc.java.caixaeletronico;

import java.util.ArrayList;
import java.util.Random;

/**
 * Class Banco.
 *
 */
public class Banco {
  private ArrayList<PessoaCliente> clientes;
  private ArrayList<Conta> contas;

  public Banco() {
    clientes = new ArrayList<PessoaCliente>();
    contas = new ArrayList<Conta>();
  }

  /**
   * Method gerarNumeroNovaConta.
   */
  public String gerarNumeroNovaConta() {
    String numeroConta = "";
    Random random = new Random();

    for (int i = 0; i < 10; i++) {
      int digito = random.nextInt(10);
      numeroConta += Integer.toString(digito);
    }

    return numeroConta;
  }

  /**
   * Method adicionarPessoaCliente.
   */
  public PessoaCliente adicionarPessoaCliente(String nome, String cpf, String senha) {
    PessoaCliente cliente = new PessoaCliente(nome, cpf, senha);
    clientes.add(cliente);
    return cliente;
  }

  public void adicionarConta(Conta conta) {
    contas.add(conta);
  }

  public ArrayList<Conta> getContas() {
    return contas;
  }

  /**
   * Method pessoaClienteLogin.
   */
  public PessoaCliente pessoaClienteLogin(String cpf, String senha) {
    for (PessoaCliente cliente : clientes) {
      if (cliente.getCpf().equals(cpf) && cliente.validarSenha(senha)) {
        return cliente;
      }
    }

    return null;
  }

  public void transferirFundos(
      PessoaCliente pessoaCliente,
      int daConta,
      int paraConta,
      double quantia) {
    this.sacar(pessoaCliente, daConta, quantia);
    this.depositar(pessoaCliente, paraConta, quantia);
  }

  public void sacar(PessoaCliente pessoaCliente, int daConta, double quantia) {
    pessoaCliente.adicionarTransacaoContaEspecifica(daConta, quantia, "Saque");
  }

  public void depositar(PessoaCliente pessoaCliente, int paraConta, double quantia) {
    pessoaCliente.adicionarTransacaoContaEspecifica(paraConta, quantia, "Depósito");
  }

  public void mostrarExtrato(PessoaCliente pessoaCliente, int conta) {
    pessoaCliente.retornarExtratoContaEspecifica(conta);
  }

}
