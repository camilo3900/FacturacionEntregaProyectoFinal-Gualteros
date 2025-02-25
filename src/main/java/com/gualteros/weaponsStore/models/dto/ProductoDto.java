package com.gualteros.weaponsStore.models.dto;

import com.gualteros.weaponsStore.models.extra.Categoria;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductoDto {
    private String nombreDto;
    private Categoria categoriaDto;
    private Double precioDto;
}
