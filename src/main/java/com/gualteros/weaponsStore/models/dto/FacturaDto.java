package com.gualteros.weaponsStore.models.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FacturaDto {
    
    String numFacturaDto;
    Double totalPagarDto;
    CompradorDto compradorDto; 
}
