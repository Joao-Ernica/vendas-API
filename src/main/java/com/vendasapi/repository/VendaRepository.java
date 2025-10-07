package com.vendasapi.repository;

import com.vendasapi.model.entity.Venda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VendaRepository extends JpaRepository<Venda, Long> {

	List<Venda> findAllByOrderByDataVendaAsc();
}
