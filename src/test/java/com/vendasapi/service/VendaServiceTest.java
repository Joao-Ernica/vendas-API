package com.vendasapi.service;

import com.vendasapi.enums.StatusVenda;
import com.vendasapi.model.dto.request.VendaRequest;
import com.vendasapi.model.entity.Venda;
import com.vendasapi.model.entity.Vendedor;
import com.vendasapi.repository.VendaRepository;
import com.vendasapi.repository.VendedorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ActiveProfiles;
import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ActiveProfiles("test")
class VendaServiceTest {

	@Mock
	private VendaRepository vendaRepository;

	@Mock
	private VendedorRepository vendedorRepository;

	@InjectMocks
	private VendaService vendaService;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	@DisplayName("Deve criar venda com sucesso")
	void deveCriarVendaComSucesso() {
		Vendedor vendedor = Vendedor.builder()
				.id(1L)
				.nome("João pedro")
				.build();

		when(vendedorRepository.findById(vendedor.getId())).thenReturn(Optional.of(vendedor));

		Venda vendaEsperada = Venda.builder()
				.vendedor(vendedor)
				.vendedorNomeSnapshot(vendedor.getNome())
				.valor(new BigDecimal("150.50"))
				.status(StatusVenda.PENDENTE)
				.build();

		when(vendaRepository.save(any(Venda.class)))
				.thenAnswer(invocation -> invocation.getArgument(0)); //preciso disso porque o metodo criarVenda precisa de uma resposta do repository

		VendaRequest request = VendaRequest.builder()
				.valor(new BigDecimal("150.50"))
				.vendedorId(vendedor.getId())
				.build();

		Venda vendaCriada = vendaService.criarVenda(request);

		assertNotNull(vendaCriada);
		assertEquals(StatusVenda.PENDENTE, vendaCriada.getStatus());
		assertEquals(new BigDecimal("150.50"), vendaCriada.getValor());
		assertEquals(vendedor.getId(), vendaCriada.getVendedor().getId());

		verify(vendaRepository, times(1)).save(any(Venda.class));
	}

	@Test
	@DisplayName("Deve lançar exceção quando venda não encontrada")
	void deveLancarExcecaoQuandoVendaNaoEncontrada() {
		IllegalArgumentException exception = assertThrows(
				IllegalArgumentException.class,
				() -> vendaService.buscarVendaPorId(999L));

		assertEquals("Venda não encontrada", exception.getMessage());
	}
}