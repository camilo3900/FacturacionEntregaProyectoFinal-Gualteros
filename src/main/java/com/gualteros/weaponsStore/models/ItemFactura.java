package com.gualteros.weaponsStore.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ItemFactura {
	Integer cantidad;
	Float totalPagar;
	
	public ItemFactura() {
	}
}
