package com.gualteros.weaponsStore.models.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ItemFacturaDto {
	String idDto;
	Integer cantidadDto;
	Double valorTotalDto;

	public ItemFacturaDto() {
	}
}
