package com.gualteros.weaponsStore.models;

import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


@MappedSuperclass
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public abstract class Persona {
	
	//clase padre
    private String dni;
    private String nombre;
    private String apellido;
    private Integer edad;

}
