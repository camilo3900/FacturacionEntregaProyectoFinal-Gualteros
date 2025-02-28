package com.gualteros.weaponsStore.models.dto;

import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FacturaDto {
    LocalDate fechaEmisionDto;
    String numFacturaDto;
    Double totalPagarDto;
    CompradorDto compradorDto; 
    List<ItemFacturaDto> itemsDto;
}
