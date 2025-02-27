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
    String direccion;
    @Column(name = "ciudad")
    String cuidad;
    @Column(name = "telefono")
    String telefono;
}
