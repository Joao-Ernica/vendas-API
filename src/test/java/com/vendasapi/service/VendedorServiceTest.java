package com.vendasapi.service;

import com.vendasapi.model.dto.response.VendedorRelatorioResponse;
import com.vendasapi.model.entity.Vendedor;
import com.vendasapi.repository.VendedorRepository;
import com.vendasapi.repository.VendaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ActiveProfiles;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ActiveProfiles("test")
class VendedorServiceTest {

	@Mock
	private VendedorRepository vendedorRepository;

	@Mock
	private VendaRepository vendaRepository;

	@InjectMocks
	private VendedorService vendedorService;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	@DisplayName("Deve buscar vendedor por ID com sucesso")
	void deveBuscarVendedorPorIdComSucesso() {
		Vendedor vendedor = Vendedor.builder()
				.id(1L)
				.nome("Joao")
				.build();

		when(vendedorRepository.findById(vendedor.getId())).thenReturn(Optional.of(vendedor));

		Vendedor vendedorEncontrado = vendedorService.buscarVendedorPorId(vendedor.getId());

		assertNotNull(vendedorEncontrado);
		assertEquals(vendedor.getId(), vendedorEncontrado.getId());
		assertEquals("Joao", vendedorEncontrado.getNome());
	}

	@Test
	@DisplayName("Deve gerar relatório de vendedor com sucesso")
	void deveGerarRelatorioVendedorComSucesso() {
		VendedorRelatorioResponse r1 = VendedorRelatorioResponse.builder()
				.nome("Vendedor 1")
				.totalVendas(new BigDecimal("300.00"))
				.mediaDiaria(new BigDecimal("150.00"))
				.build();

		VendedorRelatorioResponse r2 = VendedorRelatorioResponse.builder()
				.nome("Vendedor 2")
				.totalVendas(new BigDecimal("150.00"))
				.mediaDiaria(new BigDecimal("150.00"))
				.build();

		List<VendedorRelatorioResponse> relatorios = List.of(r1, r2);

		LocalDate dataInicial = LocalDate.now().minusDays(1);
		LocalDate dataFinal = LocalDate.now().plusDays(1);

		when(vendedorRepository.buscarRelatorioVendas(any(), any())).thenReturn(relatorios);

		List<VendedorRelatorioResponse> relatorio = vendedorService.relatorioVendedor(dataInicial, dataFinal);

		assertNotNull(relatorio);
		assertEquals(2, relatorio.size());

		VendedorRelatorioResponse vendedor1Relatorio = relatorio.stream()
				.filter(r -> r.getNome().equals("Vendedor 1"))
				.findFirst()
				.orElse(null);

		assertNotNull(vendedor1Relatorio);
		assertEquals(new BigDecimal("300.00"), vendedor1Relatorio.getTotalVendas());
	}

	@Test
	@DisplayName("Deve lançar exceção quando vendedor não encontrado")
	void deveLancarExcecaoQuandoVendedorNaoEncontrado() {
		IllegalArgumentException exception = assertThrows(
				IllegalArgumentException.class,
				() -> vendedorService.buscarVendedorPorId(9L));

		assertEquals("Vendedor não encontrado", exception.getMessage());
	}
}
