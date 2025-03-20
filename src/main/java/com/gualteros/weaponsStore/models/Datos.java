package com.gualteros.weaponsStore.models;

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
    private String direccion;
    @Column(name = "ciudad")
    private String cuidad;
    @Column(name = "telefono")
    private String telefono;
}
