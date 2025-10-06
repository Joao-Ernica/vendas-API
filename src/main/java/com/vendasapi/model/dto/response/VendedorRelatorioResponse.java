package com.vendasapi.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VendedorRelatorioResponse {

	private String nome;
	private BigDecimal totalVendas;
	private BigDecimal mediaDiaria;
}
