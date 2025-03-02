package com.gualteros.weaponsStore.models.dto;

import com.gualteros.weaponsStore.models.Categoria;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategoriaDto {
	private String nombreDto;
	private String descDto;
	
	//type conversion
	public Categoria toCategoria() {
		return Categoria.builder()
				.nombre(this.nombreDto)
				.desc(descDto)
				.build();
	}

}
