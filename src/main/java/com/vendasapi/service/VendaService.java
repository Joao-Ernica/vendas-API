package com.vendasapi.service;

import com.vendasapi.enums.StatusVenda;
import com.vendasapi.model.dto.request.VendaRequest;
import com.vendasapi.model.dto.response.VendedorRelatorioResponse;
import com.vendasapi.model.entity.Venda;
import com.vendasapi.model.entity.Vendedor;
import com.vendasapi.repository.VendaRepository;
import com.vendasapi.repository.VendedorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional
public class VendaService {

	private final VendaRepository vendaRepository;
	private final VendedorRepository vendedorRepository;

	@Transactional(readOnly = true)
	public List<Venda> listarVendas() {
		return vendaRepository.findAllByOrderByDataVendaAsc();
	}

	public Venda criarVenda(VendaRequest request) {
		Vendedor vendedor = vendedorRepository.findById(request.getVendedorId())
				.orElseThrow(() -> new IllegalArgumentException("Vendedor não encontrado"));

		Venda venda = Venda.builder()
				.vendedor(vendedor) // relaciona o objeto
				.vendedorNomeSnapshot(vendedor.getNome())
				.valor(request.getValor())
				.build();

		return vendaRepository.save(venda);
	}

	@Transactional(readOnly = true)
	public Venda buscarVendaPorId(Long id) {
		return vendaRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Venda não encontrada"));
	}

	public Venda cancelarVenda(Long id) {
		Venda venda = buscarVendaPorId(id);

		venda.checarStatus();

		venda.setStatus(StatusVenda.CANCELADA);
		return vendaRepository.save(venda);
	}
}
