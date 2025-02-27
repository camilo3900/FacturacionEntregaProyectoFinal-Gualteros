package com.gualteros.weaponsStore.models.dto;



import java.util.List;
import com.gualteros.weaponsStore.models.Producto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ProductoDto {
    private String nombreDto;
    private Double precioDto;
    private Integer stockDto;
    private List<String> categoriasDto;
    
    //type conversion
    public Producto toProducto() {
		return Producto.builder().nombre(this.nombreDto)
				.precio(this.precioDto)
				.stock(this.stockDto)
				.build();
    }
    
}
