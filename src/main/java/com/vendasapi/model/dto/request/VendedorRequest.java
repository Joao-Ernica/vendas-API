package com.vendasapi.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VendedorRequest {

	@NotBlank(message = "Nome do vendedor é obrigatório")
	private String nome;
}