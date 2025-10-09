package com.vendasapi.controller;

import com.vendasapi.mapper.VendedorMapping;
import com.vendasapi.model.dto.request.VendedorRequest;
import com.vendasapi.model.dto.response.VendedorRelatorioResponse;
import com.vendasapi.model.dto.response.VendedorResponse;
import com.vendasapi.model.entity.Vendedor;
import com.vendasapi.service.VendedorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/vendedor")
public class VendedorController {

	private final VendedorService vendedorService;
	private final VendedorMapping vendedorMapping;

	@GetMapping
	public List<VendedorResponse> listarVendedores() {
		List<Vendedor> vendas = vendedorService.listarVendedores();
		return vendedorMapping.toVendedorResponseList(vendas);
	}

	@PostMapping
	public VendedorResponse criarVendedor(@Valid @RequestBody VendedorRequest request) {
		Vendedor vendedor = vendedorService.criarVendedor(request);
		return vendedorMapping.toVendedorResponse(vendedor); // mapeia para DTO de saída
	}

	@GetMapping("{id}")
	public VendedorResponse buscarVendedorPorId(@PathVariable Long id) {
		Vendedor vendedor = vendedorService.buscarVendedorPorId(id);
		return vendedorMapping.toVendedorResponse(vendedor);
	}

	@PatchMapping("{id}/nome")
	public VendedorResponse atualizarNomeVendedor(@PathVariable Long id, @RequestBody String novoNome) {
		Vendedor vendedor = vendedorService.atualizarNomeVendedor(id, novoNome);
		return vendedorMapping.toVendedorResponse(vendedor);
	}

	@GetMapping("relatorio")
	public List<VendedorRelatorioResponse> relatorioVendedor(
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicial,
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFinal) {

		return vendedorService.relatorioVendedor(dataInicial, dataFinal);
	}
}
