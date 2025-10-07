package com.vendasapi.service;

import com.vendasapi.mapper.VendaMapping;
import com.vendasapi.model.dto.request.VendaRequest;
import com.vendasapi.model.entity.Venda;
import com.vendasapi.model.entity.Vendedor;
import com.vendasapi.repository.VendaRepository;
import com.vendasapi.repository.VendedorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class VendaService {

	private final VendaRepository vendaRepository;
	private final VendedorRepository vendedorRepository;
	private final VendaMapping vendaMapping;

	@Transactional(readOnly = true)
	public List<Venda> listarVendas(){
		return vendaRepository.findAllByOrderByDataVendaAsc();
	}

	public Venda criarVenda(VendaRequest request){
		Vendedor vendedor = vendedorRepository.findById(request.getVendedorId())
				.orElseThrow(() -> new IllegalArgumentException("Vendedor não encontrado"));

		Venda venda = Venda.builder()
				.vendedor(vendedor) // relaciona o objeto
				.vendedorNomeSnapshot(vendedor.getNome())
				.valor(request.getValor())
				.build();

		return vendaRepository.save(venda);
	}
}
