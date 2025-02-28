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

@Entity
@Data
@Builder
@Table(name = "items_factura")
@AllArgsConstructor
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
	
	public ItemFactura() {
	}
	
	
	public Float calcularTotalItem() {
		return producto.getPrecio().floatValue() * cantidad;
	}
	
	public ItemFacturaDto toItemFacturaDto() {
		return ItemFacturaDto.builder().idDto(this.id.toString()).cantidadDto(this.cantidad)
				.valorTotalDto(this.valorTotal.doubleValue()).build();
	}
}
