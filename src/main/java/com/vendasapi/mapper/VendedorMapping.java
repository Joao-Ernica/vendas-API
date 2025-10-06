package com.vendasapi.mapper;

import com.vendasapi.model.dto.request.VendedorRequest;
import com.vendasapi.model.dto.response.VendedorResponse;
import com.vendasapi.model.entity.Vendedor;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class VendedorMapping {

    private final ModelMapper mapper;

    public Vendedor toVendedor(VendedorRequest request) {
        return mapper.map(request, Vendedor.class);
    }

    public VendedorResponse toVendedorResponse(Vendedor vendedor) {
        return mapper.map(vendedor, VendedorResponse.class);
    }

    public List<VendedorResponse> toVendedorResponseList(List<Vendedor> vendedores) {
        return vendedores
                .stream()
                .map(this::toVendedorResponse)
                .collect(Collectors.toList());
    }
}
