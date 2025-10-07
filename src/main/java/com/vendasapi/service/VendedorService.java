package com.vendasapi.service;

import com.vendasapi.mapper.VendedorMapping;
import com.vendasapi.model.dto.request.VendedorRequest;
import com.vendasapi.model.entity.Vendedor;
import com.vendasapi.repository.VendedorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class VendedorService {

	private final VendedorRepository vendedorRepository;
	private final VendedorMapping vendedorMapping;

	@Transactional(readOnly = true)
	public List<Vendedor> listarVendedores(){
		return vendedorRepository.findAllByOrderByNomeAsc();
	}

	public Vendedor criarVendedor(VendedorRequest request) {
		Vendedor vendedor = Vendedor.builder()
				.nome(request.getNome())
				.build();

		return vendedorRepository.save(vendedor);
	}
}
