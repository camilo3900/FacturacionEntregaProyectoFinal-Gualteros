package com.gualteros.weaponsStore.models.extra;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.NoArgsConstructor;
@Embeddable //aplica para atributos tipo clase
@Data
@NoArgsConstructor
public class Datos {

    @Column(name = "direccion")
    String direccion;
    @Column(name = "ciudad")
    String cuidad;
    @Column(name = "telefono")
    String telefono;
}
