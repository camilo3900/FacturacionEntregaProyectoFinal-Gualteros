package com.gualteros.weaponsStore.models.extra;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
