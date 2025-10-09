package com.vendasapi.config;

import com.vendasapi.model.entity.Venda;
import com.vendasapi.model.entity.Vendedor;
import com.vendasapi.repository.VendaRepository;
import com.vendasapi.repository.VendedorRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.math.BigDecimal;
import java.util.List;

@Configuration
@EnableJpaAuditing
@Profile("test")
public class testConfig implements CommandLineRunner {

	@Autowired
	private VendedorRepository vendedorRepository;

	@Autowired
	private VendaRepository vendaRepository;

	@Override
	@SneakyThrows
	public void run(String... args) {

		Vendedor vendedor = Vendedor.builder()
				.nome("Johnny test").build();

		Vendedor vendedor2 = Vendedor.builder()
				.nome("Ana").build();

		vendedorRepository.saveAll(List.of(vendedor, vendedor2));

		Venda venda = Venda.builder()
				.valor(new BigDecimal(200))
				.vendedorNomeSnapshot("Johnny test")
				.vendedor(vendedor).build();

		Venda venda2 = Venda.builder()
				.valor(new BigDecimal(500))
				.vendedorNomeSnapshot("Johnny test")
				.vendedor(vendedor).build();

		Venda venda3 = Venda.builder()
				.valor(new BigDecimal(200))
				.vendedorNomeSnapshot("Ana")
				.vendedor(vendedor).build();

		vendaRepository.saveAll(List.of(venda, venda2, venda3));
	}
}
