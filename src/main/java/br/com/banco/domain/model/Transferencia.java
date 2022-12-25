package br.com.banco.domain.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Transferencia {

  @EqualsAndHashCode.Include
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  @CreationTimestamp
  @Column(columnDefinition = "datetime")
  private LocalDate dataTransferencia;

  private BigDecimal valor;

  @Column(name = "tipo")
  @Enumerated(value = EnumType.STRING)
  private Tipo tipo;

  private String nomeOperadorTransacao;

  @ManyToOne
  @JoinColumn(name = "conta_id")
  @JsonIgnore
  private Conta conta;
}
