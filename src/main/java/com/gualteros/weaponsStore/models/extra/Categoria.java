package com.gualteros.weaponsStore.models.extra;

//ESTE ENUM NOS PERMITE CLASIFICAR LOS PRODUCTOS
public enum Categoria {
    RIFLES("RIFLES"),
    PISTOLAS("PISTOLAS"),
    EQUIPOS("EQUIPOS"),
    MUNICIONES("MUNICIONES"),
    DAGAS("DAGAS"),
    CAMUFLAJE("CAMUFLAJE"),
    SISTEMAS_DE_SEGURIDAD("SISTEMAS DE SEGURIDAD");
    
    private String name;
 
    Categoria(String name) {
        this.name = name;
    }
 
    public String getName() {
        return name;
    }
}
