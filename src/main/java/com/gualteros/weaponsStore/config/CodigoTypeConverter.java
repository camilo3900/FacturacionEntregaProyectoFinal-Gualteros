package com.gualteros.weaponsStore.config;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.AttributeConverter;

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
