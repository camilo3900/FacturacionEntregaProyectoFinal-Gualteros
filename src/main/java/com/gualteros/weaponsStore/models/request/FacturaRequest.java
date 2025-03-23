package com.gualteros.weaponsStore.models.request;

import java.time.LocalDate;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;


@Data
public class FacturaRequest {
    @Schema(description = "Id cliente", example = "3")
    private Long idCliente;
    private LocalDate fecha;
    private List<ItemFacturaRequest> itemsRequest;
}

