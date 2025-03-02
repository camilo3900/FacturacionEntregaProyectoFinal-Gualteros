package com.gualteros.weaponsStore.models;

import java.util.List;

import com.gualteros.weaponsStore.models.dto.ClienteDto;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "clientes")
public class Cliente extends Persona {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@Embedded
	private Datos datos;//clase embebida dentro de cliente
	@OneToMany(mappedBy = "cliente"
			, cascade = CascadeType.ALL
			, fetch = FetchType.EAGER)
	private List<Factura> facturas;

	//TYPE CONVERSION
	public ClienteDto toClienteDto() {
		return ClienteDto.builder().nombreDto(this.getNombre())
				.apellidoDto(this.getApellido())
				.dfacturaDto(facturas.stream()
						.map((it) -> it.toFacturaDto())
						.toList())
				.build();
	}
}
