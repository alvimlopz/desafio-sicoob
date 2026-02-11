package com.example.backend.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;

public class BeneficioRequestDTO {

    @NotBlank
    @Size(max = 100)
    private String nome;

    @Size(max = 255)
    private String descricao;

    @NotNull
    @DecimalMin(value = "0.00")
    private BigDecimal valor;

    private Boolean ativo = true;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

}
