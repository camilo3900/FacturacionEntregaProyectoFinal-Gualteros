package com.gualteros.weaponsStore.models.dto;


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
    String fechaEmisionDto;
    String numFacturaDto;
    Double totalPagarDto;
    String compradorDto; 
    List<ItemFacturaDto> itemsDto;
    
   
}
