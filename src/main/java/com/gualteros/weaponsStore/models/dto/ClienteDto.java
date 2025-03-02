package com.gualteros.weaponsStore.models.dto;

import com.gualteros.weaponsStore.models.Cliente;
import java.util.ArrayList;
import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ClienteDto {
	
    String nombreDto;
    String apellidoDto;
    List<FacturaDto> dfacturaDto;

	
    //type conversion
    public Cliente toCliente(){
        return Cliente.builder()
        .nombre(this.nombreDto)
        .apellido(this.apellidoDto)
        .facturas(new ArrayList<>()).build();
    }

}
