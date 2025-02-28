package com.gualteros.weaponsStore.config;

import java.time.LocalDate;

import jakarta.persistence.AttributeConverter;

public class DateTypeConverter implements AttributeConverter<LocalDate, Long> {

	@Override
	public Long convertToDatabaseColumn(LocalDate localDate) {
		return localDate.toEpochDay();
	}

	@Override
	public LocalDate convertToEntityAttribute(Long localDateLong) {
		return LocalDate.ofEpochDay(localDateLong);
	}

}
