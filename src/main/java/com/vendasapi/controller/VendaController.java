package com.vendasapi.controller;

import com.vendasapi.mapper.VendaMapping;
import com.vendasapi.model.dto.request.VendaRequest;
import com.vendasapi.model.dto.response.VendaResponse;
import com.vendasapi.model.dto.response.VendedorRelatorioResponse;
import com.vendasapi.model.entity.Venda;
import com.vendasapi.service.VendaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("api/vendas")
@RequiredArgsConstructor
public class VendaController {

	private final VendaService vendaService;
	private final VendaMapping vendaMapping;

	@GetMapping
	public List<VendaResponse> listarVendas() {
		List<Venda> vendas = vendaService.listarVendas();
		return vendaMapping.toVendaResponseList(vendas);
	}

	@PostMapping
	public VendaResponse criarVenda(@Valid @RequestBody VendaRequest request) {
		Venda venda = vendaService.criarVenda(request);
		return vendaMapping.toVendaResponse(venda);
	}

	@GetMapping("{id}")
	public VendaResponse buscarVendaPorId(@PathVariable Long id) {
		Venda venda = vendaService.buscarVendaPorId(id);
		return vendaMapping.toVendaResponse(venda);
	}

	@PatchMapping("{id}/cancelar")
	public VendaResponse cancelarVenda(@PathVariable Long id) {
		Venda venda = vendaService.cancelarVenda(id);
		return vendaMapping.toVendaResponse(venda);
	}
}
