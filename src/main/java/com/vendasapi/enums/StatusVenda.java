package com.vendasapi.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum StatusVenda {

	PENDENTE("Pendente", "Venda aguardando processamento"),
	CONFIRMADA("Confirmada", "Venda confirmada e processada"),
	CANCELADA("Cancelada", "Venda cancelada"),
	FINALIZADA("Finalizada", "Venda finalizada com sucesso");

	private final String descricao;
	private final String detalhes;
}
