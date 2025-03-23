package com.gualteros.weaponsStore.utils;


import java.util.UUID;
import jakarta.persistence.AttributeConverter;
//converter para codigo de facturas
public class CodigoTypeConverter implements AttributeConverter<UUID, String> {

	@Override
	public String convertToDatabaseColumn(UUID code) {
		return code.toString();
	}

	@Override
	public UUID convertToEntityAttribute(String stringCode) {
		return UUID.fromString(stringCode);
	}

}
