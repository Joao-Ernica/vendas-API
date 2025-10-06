package com.vendasapi.model.entity;

import com.vendasapi.enums.StatusVenda;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "vendas")
public class Venda {

	// id, dataVenda, valor
	// relação ManyToOne com Vendedor

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Setter(AccessLevel.NONE)
	@EqualsAndHashCode.Include
	private long id;

	@NotNull
	@Positive
	private BigDecimal valor;

	@CreationTimestamp
	@Column(name = "data_venda", nullable = false)
	private LocalDateTime dataVenda;

	@Enumerated(EnumType.STRING)
	@Builder.Default
	private StatusVenda status = StatusVenda.PENDENTE;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "vendedor_id")
	private Vendedor vendedor;

	@UpdateTimestamp
	@Column(name = "data_atualizacao")
	private LocalDateTime dataAtualizacao;

	// Snapshot serve para guardar o nome na venda caso o vendedor o mude
	@Column(name = "vendedor_nome_snapshot", length = 120, nullable = false)
	private String vendedorNomeSnapshot;
}
