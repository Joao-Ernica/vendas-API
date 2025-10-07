package com.vendasapi.mapper;

import com.vendasapi.model.dto.request.VendaRequest;
import com.vendasapi.model.dto.response.VendaResponse;
import com.vendasapi.model.entity.Venda;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class VendaMapping {

    private final ModelMapper mapper;

    public Venda toVenda(VendaRequest request) {
        return mapper.map(request, Venda.class);
    }

	public VendaResponse toVendaResponse(Venda venda) {
		VendaResponse response = mapper.map(venda, VendaResponse.class);
		response.setVendedorNome(venda.getVendedorNomeSnapshot());
		return response;
	}

    public List<VendaResponse> toVendaResponseList(List<Venda> vendas) {
        return vendas
                .stream()
                .map(this::toVendaResponse)
                .collect(Collectors.toList());
    }
}
