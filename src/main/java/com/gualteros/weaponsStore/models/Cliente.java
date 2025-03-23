package com.gualteros.weaponsStore.models;

import java.util.List;

import com.gualteros.weaponsStore.models.dto.ClienteDto;

import io.swagger.v3.oas.annotations.media.Schema;
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
	@Schema(description = "Identificador unico", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
	private Long id;
	@Embedded
	private Datos datos;//clase embebida dentro de cliente
	@OneToMany(mappedBy = "cliente"
			, cascade = CascadeType.ALL
			, fetch = FetchType.EAGER)
	@Schema(description = "Lista facturas", example = "[]")
	private List<Factura> facturas;


	public void actualizarCliente(ClienteDto clienteNuevo){
		this.setNombre(clienteNuevo.getNombreDto());
		this.setApellido(clienteNuevo.getApellidoDto());
		this.setDni(clienteNuevo.getDniDto());
		this.setEdad(clienteNuevo.getEdadDto());
	}

	//TYPE CONVERSION
	public ClienteDto toClienteDto() {
		return ClienteDto.builder().nombreDto(this.getNombre())
				.apellidoDto(this.getApellido())
				.edadDto(this.getEdad())
				.dniDto(this.getDni())
				.dfacturaDto(facturas.stream()
						.map((it) -> it.toFacturaDto())
						.toList())
				.build();
	}
}
