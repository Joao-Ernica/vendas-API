package com.vendasapi.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import jakarta.validation.constraints.NotBlank;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "vendedor")
public class Vendedor {

	// id, Nome

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Setter(AccessLevel.NONE)
	@EqualsAndHashCode.Include
	private long id;

	@Column(nullable = false, length = 120)
	@NotBlank
	private String nome;

	@OneToMany(mappedBy = "vendedor", fetch = FetchType.LAZY)
	private List<Venda> vendas;
}
