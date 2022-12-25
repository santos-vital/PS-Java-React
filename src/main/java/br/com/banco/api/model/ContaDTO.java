package br.com.banco.api.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

import br.com.banco.domain.model.Transferencia;
import lombok.Data;

@Data
public class ContaDTO {

  @Id
  private Long id;

  @NotBlank
  private String nome;

  private List<Transferencia> transferencias = new ArrayList<>();
}
