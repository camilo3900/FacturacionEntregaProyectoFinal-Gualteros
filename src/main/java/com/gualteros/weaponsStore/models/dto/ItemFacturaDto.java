package com.gualteros.weaponsStore.models.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor@NoArgsConstructor
@Builder
public class ItemFacturaDto {
	
	Integer cantidadDto;
	Double valorTotalDto;
	String productoDto;


}
