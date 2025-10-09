package com.vendasapi.repository;

import com.vendasapi.model.dto.response.VendedorRelatorioResponse;
import com.vendasapi.model.entity.Vendedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface VendedorRepository extends JpaRepository<Vendedor, Long> {

    List<Vendedor> findAllByOrderByNomeAsc();

	@Query("""
    SELECT new com.vendasapi.model.dto.response.VendedorRelatorioResponse(
        v.nome,
        SUM(vd.valor),
        SUM(vd.valor) / COUNT(DISTINCT FUNCTION('FORMATDATETIME', vd.dataVenda, 'yyyy-MM-dd'))
    )
    FROM Venda vd
    JOIN vd.vendedor v
    WHERE vd.dataVenda BETWEEN :dataInicial AND :dataFinal
    GROUP BY v.nome
""")
	List<VendedorRelatorioResponse> buscarRelatorioVendas(
			@Param("dataInicial") LocalDateTime dataInicial,
			@Param("dataFinal") LocalDateTime dataFinal );
}
