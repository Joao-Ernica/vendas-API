package com.vendasapi.repository;

import com.vendasapi.model.entity.Vendedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VendedorRepository extends JpaRepository<Vendedor, Long> {

	List<Vendedor> findAllByOrderByNomeAsc();
}
