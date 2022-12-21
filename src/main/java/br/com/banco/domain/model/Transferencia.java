package br.com.banco.domain.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Transferencia {

  @EqualsAndHashCode.Include
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  @CreationTimestamp
  @Column(nullable = false, columnDefinition = "datetime")
  private LocalDate dataTransferencia;

  @Column(nullable = false)
  private BigDecimal valor;

  @Column(nullable = false)
  private String tipo;

  private String nomeOperadorTransacao;

  @ManyToOne
  @JoinColumn(name = "conta_id", nullable = false)
  private Conta conta;
}
