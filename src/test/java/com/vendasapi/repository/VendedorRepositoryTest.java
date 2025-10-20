package com.vendasapi.repository;

import com.vendasapi.config.TestConfig;
import com.vendasapi.model.dto.response.VendedorRelatorioResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@ActiveProfiles("test")
@Import(TestConfig.class)
class VendedorRepositoryTest {

	@Autowired
	private VendedorRepository vendedorRepository;

	@Test
	void buscarRelatorioVendas() {
		LocalDateTime dataInicial = LocalDateTime.now().minusDays(1); //tira um dia da data
		LocalDateTime dataFinal = LocalDateTime.now().plusDays(1); //adiciona um dia na data

		List<VendedorRelatorioResponse> resultado = vendedorRepository.buscarRelatorioVendas(dataInicial, dataFinal);

		VendedorRelatorioResponse johnny = resultado.stream()
				.filter(r -> r.getNome().equals("Johnny test"))
				.findFirst()
				.orElseThrow();

		assertThat(johnny).isNotNull();
		assertThat(johnny.getNome()).isEqualTo("Johnny test");
		assertThat(johnny.getTotalVendas()).isEqualByComparingTo("700");
	}
}
