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
	@Column(name = "data_de_atualizacao")
	private LocalDateTime dataAtualizacao;

	@Column(name = "vendedor_nome_snapshot", length = 120, nullable = false)
	private String vendedorNomeSnapshot;

	public void checarStatus() {
		if (this.status == StatusVenda.CANCELADA) {
			throw new IllegalArgumentException("Venda já está cancelada");
		}

		if (this.status == StatusVenda.FINALIZADA) {
			throw new IllegalArgumentException("Não é possível cancelar uma venda finalizada");
		}
	}
}
