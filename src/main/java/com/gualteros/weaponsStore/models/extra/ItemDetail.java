package com.gualteros.weaponsStore.models.extra;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Embeddable
@NoArgsConstructor@AllArgsConstructor
@Builder
public class ItemDetail {
    private Long productoId;
    private String nombreProducto;
    private Double precioUnitario;
}
