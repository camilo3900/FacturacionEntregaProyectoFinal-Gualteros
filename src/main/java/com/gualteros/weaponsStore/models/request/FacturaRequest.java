package com.gualteros.weaponsStore.models.request;

import java.time.LocalDate;
import java.util.List;

import lombok.Data;


@Data
public class FacturaRequest {
    private Long idCliente;
    private LocalDate fecha;
    private List<ItemFacturaRequest> itemsRequest;
}

