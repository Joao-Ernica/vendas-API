package com.vendasapi.service;

import com.vendasapi.model.dto.request.VendedorRequest;
import com.vendasapi.model.entity.Vendedor;
import com.vendasapi.repository.VendedorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class VendedorService {

	private final VendedorRepository vendedorRepository;

	@Transactional(readOnly = true)
	public List<Vendedor> listarVendedores() {
		return vendedorRepository.findAllByOrderByNomeAsc();
	}

	public Vendedor criarVendedor(VendedorRequest request) {
		Vendedor vendedor = Vendedor.builder()
				.nome(request.getNome())
				.build();

		return vendedorRepository.save(vendedor);
	}

	@Transactional(readOnly = true)
	public Vendedor buscarVendedorPorId(Long id) {
		return vendedorRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Vendedor não encontrado"));
	}


	public Vendedor atualizarNomeVendedor(Long id, String novoNome) {
		Vendedor vendedor = buscarVendedorPorId(id);
		vendedor.setNome(novoNome);
		return vendedorRepository.save(vendedor);
	}
}
