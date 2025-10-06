package com.vendasapi.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum StatusVenda {

	PENDENTE(1,"Pendente", "Venda aguardando processamento"),
	CONFIRMADA(2, "Confirmada", "Venda confirmada e processada"),
	CANCELADA(3, "Cancelada", "Venda cancelada"),
	FINALIZADA(4, "Finalizada", "Venda finalizada com sucesso");

	private final Integer codigo;
	private final String descricao;
	private final String detalhes;
}
