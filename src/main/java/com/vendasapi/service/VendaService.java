package com.vendasapi.service;

import com.vendasapi.mapper.VendaMapping;
import com.vendasapi.model.entity.Venda;
import com.vendasapi.repository.VendaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class VendaService {

	private final VendaRepository repository;
	private final VendaMapping mapping;

	@Transactional(readOnly = true)
	public List<Venda> listarVendas(){
		return repository.findAllByOrderByDataVendaAsc();
	}
}
