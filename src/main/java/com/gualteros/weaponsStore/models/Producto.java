package com.gualteros.weaponsStore.models;

import java.util.List;

import com.gualteros.weaponsStore.models.dto.ProductoDto;
import com.gualteros.weaponsStore.models.dto.ProductoDto;
import com.gualteros.weaponsStore.models.extra.Categoria;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "productos")
@Data
@Builder
@AllArgsConstructor@NoArgsConstructor
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producto")
    private Long id;
    @Column(name = "nombre_producto")
    private String nombre;
    @Column(name = "precio_producto")
    private Double precio;
    @Column(name = "stock")
    private Integer stock;
    @Nullable
    @ManyToMany(mappedBy = "productos", fetch = FetchType.EAGER)
    List<Factura> facturas;

    @ManyToMany
    @JoinTable(name = "producto_categoria"
    , joinColumns = @JoinColumn(name = "producto_id")
    , inverseJoinColumns = @JoinColumn(name = "categoria_id"))
    private List<Categoria> categorias;
    //type conversion
    public ProductoDto toProductoDto() {
        return ProductoDto.builder().nombreDto(this.nombre)
        		.precioDto(this.precio).stockDto(this.stock)
        		.categoriasDto(this.categorias.stream()
        				.map(it->it.getName()).toList()).build();
    }
}

