package com.vendasapi.service;

import com.vendasapi.mapper.VendedorMapping;
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

	private final VendedorRepository repository;
	private final VendedorMapping mapping;

	@Transactional(readOnly = true)
	public List<Vendedor> listarVendedores(){
		return repository.findAllByOrderByNomeAsc();
	}
}
