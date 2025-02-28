package com.gualteros.weaponsStore.models;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gualteros.weaponsStore.config.CodigoTypeConverter;
import com.gualteros.weaponsStore.config.DateTypeConverter;
import com.gualteros.weaponsStore.models.dto.FacturaDto;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Entity
@Table(name = "facturas")
@Data
@Builder
@AllArgsConstructor
public class Factura {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
//    @JsonFormat(shape = JsonFormat.Shape.STRING) 
    @Convert(converter = CodigoTypeConverter.class)
    @Column(name = "codigo_venta")
    UUID codigo;
    @Column(name="fecha_emision")
    @Convert(converter = DateTypeConverter.class)
    private LocalDate fechaEmision;
    @Column(name="pagar")
    Double totalPagar;
    @OneToMany(mappedBy = "factura"
    		, cascade = CascadeType.ALL
    		, fetch = FetchType.EAGER)
    private List<ItemFactura> items;
    


	public Factura() {
		this.fechaEmision = LocalDate.now();
	}

	
	public void calcularPago(){
         this.items.forEach(it-> {
            this.totalPagar+= it.getValorTotal();
        });
       
    }
	
	public FacturaDto toFacturaDto() {
		return FacturaDto.builder().fechaEmisionDto(this.fechaEmision)
				.numFacturaDto(this.codigo.toString())
				.totalPagarDto(this.totalPagar)
				.itemsDto(this.items.stream().map(it -> it.toItemFacturaDto())
						.toList())
				.build();
	}


}
