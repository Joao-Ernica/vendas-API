package com.vendasapi.repository;

import com.vendasapi.config.TestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@Import(TestConfig.class)
class VendaRepositoryTest {

	@Autowired
	private VendaRepository vendaRepository;

	@Test
	void deveListarVendasEmOrdem() {
		var vendas = vendaRepository.findAllByOrderByDataVendaAsc();

		assertEquals(3, vendas.size());
		assertTrue(vendas.get(0).getValor().compareTo(vendas.get(1).getValor()) < 0);
	}
}
