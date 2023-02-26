package com.trybe.acc.java.caixaeletronico;

import java.util.ArrayList;

/**
 * Class PessoaCliente.
 *
 */
public class PessoaCliente {
  private String nome;
  private String cpf;
  private String senha;
  private ArrayList<Conta> contas;

  /**
   * Constructor Class PessoaCliente.
   */
  public PessoaCliente(String nome, String cpf, String senha) {
    this.nome = nome;
    this.cpf = cpf;
    this.senha = senha;
    this.contas = new ArrayList<Conta>();
    System.out.println("Nova pessoa cliente " + this.nome + " com CPF: " + this.cpf + " criada!");
  }

  public String getCpf() {
    return this.cpf;
  }

  public void adicionarConta(Conta conta) {
    this.contas.add(conta);
  }

  public int retornarNumeroDeContas() {
    return this.contas.size();
  }

  public String retornarIdContaEspecifica(int indiceConta) {
    return this.contas.get(indiceConta).getIdConta();
  }

  public double retornarSaldoContaEspecifica(int indiceConta) {
    return this.contas.get(indiceConta).retornarSaldo();
  }

  public void retornarExtratoContaEspecifica(int indiceConta) {
    this.contas.get(indiceConta).retornarExtrato();
  }

  public void adicionarTransacaoContaEspecifica(int indiceConta, double quantia, String descricao) {
    this.contas.get(indiceConta).adicionarTransacao(quantia, descricao);
  }

  public boolean validarSenha(String senha) {
    return this.senha.equals(senha);
  }

  /**
   * Method retornarResumoContas.
   */
  public void retornarResumoContas() {
    for (Conta conta : this.contas) {
      System.out.println(conta.retornarResumoConta());
    }
  }

}
