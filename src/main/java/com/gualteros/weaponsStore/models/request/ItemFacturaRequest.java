package com.gualteros.weaponsStore.models.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ItemFacturaRequest {
    @Schema(description = "id del producto", example = "4")
    private Long productoId;
    @Schema(description = "cantidad para llevar", example = "1")
    private Integer cantidad;
}
