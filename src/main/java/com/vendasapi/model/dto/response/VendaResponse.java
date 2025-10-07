package com.vendasapi.model.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.vendasapi.enums.StatusVenda;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VendaResponse {

	private String vendedorNome;

	private BigDecimal valor;

	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private LocalDateTime dataVenda;

	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private LocalDateTime dataAtualizacao;

	private StatusVenda status;
}
