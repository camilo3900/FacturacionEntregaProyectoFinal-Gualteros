package com.gualteros.weaponsStore.models;

import io.swagger.v3.oas.annotations.media.Schema;
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
    @Schema(description = "DNI del cliente", example = "18492833")
    private String dni;
    @Schema(description = "Nombre del cliente", example = "Pepe")
    private String nombre;
    @Schema(description = "Apellido del cliente", example = "Frog")
    private String apellido;
    @Schema(description = "Edad del cliente", example = "10")
    private Integer edad;

}
