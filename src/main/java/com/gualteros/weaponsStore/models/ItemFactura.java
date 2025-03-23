package com.gualteros.weaponsStore.models;

import java.lang.invoke.VarHandle.AccessMode;

import com.gualteros.weaponsStore.models.dto.ItemFacturaDto;
import com.gualteros.weaponsStore.models.extra.ItemDetail;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
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
@Table(name = "item_facturas")
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "producto de factura")
public class ItemFactura {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Schema(description = "identificador unico de item", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;
    @Column(name = "cantidad_items")
    @Schema(description = "cantidad a pagar", example = "1")
    private Integer cantidad;
    @Column(name = "total_item")
    @Schema(description = "total a pagar por item", example = "0.0")
    private Double totalItem;
    @ManyToOne
    @JoinColumn(name="factura_id")
    private Factura factura;
    @Embedded
    private ItemDetail itemDetail;

    public Double calcularTotalPagarItem(){
        return  itemDetail.getPrecioUnitario()*cantidad;
    }



    public ItemFacturaDto toItemFacturaDto(){
        return ItemFacturaDto.builder().cantidadDto(this.cantidad).productoDto(itemDetail.getNombreProducto())
        .totalItemDto(this.totalItem).build();
    }


}
