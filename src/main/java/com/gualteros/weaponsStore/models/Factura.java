package com.gualteros.weaponsStore.models;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import com.gualteros.weaponsStore.config.CodigoTypeConverter;
import com.gualteros.weaponsStore.models.dto.FacturaDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Entity
@Table(name = "facturas")
@Data
@Builder
@AllArgsConstructor
public class Factura {
	/*Relacion:
	 * 1-factura-itemFactura = OneToMany
	 * 2-factura-cliente= ManyToOne*/
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
//    @JsonFormat(shape = JsonFormat.Shape.STRING) 
    @Convert(converter = CodigoTypeConverter.class)
    @Column(name = "codigo_venta")
    private UUID codigo;
    @Column(name="fecha_emision")
    private LocalDate fechaEmision;
    @Column(name="pagar")
    private Double totalPagar;
    @OneToMany(mappedBy = "factura"
    		, cascade = CascadeType.ALL
    		, fetch = FetchType.EAGER)
    private List<ItemFactura> items;
    @ManyToOne(optional = true)
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;


	public Factura() {
		this.fechaEmision = LocalDate.now();
	}

	//calcula el pago total
	public void calcularPago(){
         this.items.forEach(it-> {
            this.totalPagar+= it.getTotalItem();
        });
       
    }

	public void agregarItem(ItemFactura item){
		this.setFechaEmision(LocalDate.now());
		this.items.add(item);
	}
	public void eliminarItem(ItemFactura item){
		this.items.remove(item);
	}
	//type converter
	public FacturaDto toFacturaDto() {
		return FacturaDto.builder()
				.fechaEmisionDto((this.fechaEmision).toString())
				.numFacturaDto(this.codigo.toString())
				.totalPagarDto(this.totalPagar)
				.itemsDto(this.items.stream().map(it -> it.toItemFacturaDto()
						).toList())
				.compradorDto(String.format("%s %s", cliente.getNombre(), cliente.getApellido()))
				.build();
	}


}
