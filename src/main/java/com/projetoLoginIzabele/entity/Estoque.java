package com.projetoLoginIzabele.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Estoque {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String localizacao;

	private Double quantidade;

	@OneToOne
    @JoinColumn(name = "id_produto", nullable = false) // Mapeia a coluna da chave estrangeira
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Produtos produto;
}
