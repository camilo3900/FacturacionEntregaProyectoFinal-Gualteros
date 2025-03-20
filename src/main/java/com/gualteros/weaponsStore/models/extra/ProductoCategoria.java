package com.gualteros.weaponsStore.models.extra;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@AllArgsConstructor@NoArgsConstructor
public class ProductoCategoria {
    private Long idProducto;
    private Long idCategoria;
}
