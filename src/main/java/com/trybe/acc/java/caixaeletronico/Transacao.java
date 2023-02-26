package com.trybe.acc.java.caixaeletronico;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Class Trasacao.
 *
 */
public class Transacao {
  private double quantia;
  private String instante;
  private String descricao;

  /**
   * Contructor Class Transacao.
   */
  public Transacao(double quantia, String descricao) {
    this.quantia = quantia;
    this.instante = retornarInstante();
    this.descricao = descricao;
  }

  public double getQuantia() {
    return this.quantia;
  }

  public String getDescricao() {
    return this.descricao;
  }

  public String retornarResumoTransacao() {
    return this.descricao + " de " + this.quantia + " em " + this.instante;
  }

  /**
   * Method retornarInstante.
   */
  public String retornarInstante() {
    LocalDateTime agora = LocalDateTime.now();
    DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    return agora.format(formato);
  }

}
