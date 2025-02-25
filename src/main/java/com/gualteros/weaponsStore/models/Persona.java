package com.gualteros.weaponsStore.models;

import lombok.Data;

@Data
public abstract class Persona {

    private String nombre;
    private String apellido;
    private Long dni;
    private Integer edad;

}
