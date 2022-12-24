package br.com.banco.domain.model.dto;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class ContaDTO {

  @NotBlank
  private String nome;
}
