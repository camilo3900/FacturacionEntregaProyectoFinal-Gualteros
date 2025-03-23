package com.gualteros.weaponsStore.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Datos {

    @Column(name = "direccion")
    @Schema(description = "domicilio donde vive", example = "221B Baker Street")
    private String direccion;
    @Column(name = "ciudad")
    @Schema(description = "ciudad donde vive", example = "London")
    private String cuidad;
    @Column(name = "telefono")
    @Schema(description = "telefono del cliente", example = "212-555-7890")
    private String telefono;
}
