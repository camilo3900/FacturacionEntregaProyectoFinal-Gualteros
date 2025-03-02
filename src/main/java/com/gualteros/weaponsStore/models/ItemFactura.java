package com.gualteros.weaponsStore.models;

import com.gualteros.weaponsStore.models.dto.ItemFacturaDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@Table(name = "items_factura")
@AllArgsConstructor@NoArgsConstructor
/*Clase intermedia que relaciona  productos y facturas*/
public class ItemFactura {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "item_id")
	Integer id;
	@Column(name = "cantidad")
	Integer cantidad;
	@Column(name = "valor_total_item")
	Float valorTotal;
	@ManyToOne
	@JoinColumn(name = "factura_id")
	private Factura factura;
	@ManyToOne
	@JoinColumn(name="producto_id")
	    private Producto producto;
	

	//calcula cantidad a comprar
	public Float calcularTotalItem() {
		return producto.getPrecio().floatValue() * cantidad;
	}
	// type conversion
	public ItemFacturaDto toItemFacturaDto() {
		return ItemFacturaDto.builder().cantidadDto(this.cantidad)
				.valorTotalDto(this.valorTotal.doubleValue())
				.productoDto(this.producto.getNombre())
				.build();
	}
}
