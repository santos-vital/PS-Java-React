package br.com.banco.api.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class TransferenciaDTO {

  private Long id;

  @NotNull
  private LocalDate dataTransferencia;

  @NotNull
  private BigDecimal valor;

  @NotNull
  private String tipo;

  private String nomeOperadorTransacao;

  @NotNull
  private Long conta;
}
