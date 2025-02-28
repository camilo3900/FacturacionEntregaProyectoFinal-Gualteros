package com.gualteros.weaponsStore.models;

import java.util.List;

import com.gualteros.weaponsStore.models.dto.ProductoDto;
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
    @OneToMany(mappedBy="producto", cascade= CascadeType.ALL, fetch = FetchType.EAGER)
    private List<ItemFactura> itemFactura;
    @ManyToMany
    @JoinTable(name = "producto_categoria"
    , joinColumns = @JoinColumn(name = "producto_id")
    , inverseJoinColumns = @JoinColumn(name = "categoria_id"))
    private List<Categoria> categorias;
    
    
    public void vender(Integer cantidad){
        if(stock >= cantidad){
            this.stock -= cantidad;
        }else{
            throw new RuntimeException("No hay stock suficiente");
        }
    }
    //type conversion
    public ProductoDto toProductoDto() {
        return ProductoDto.builder().nombreDto(this.nombre)
        		.precioDto(this.precio).stockDto(this.stock)
        		.categoriasDto(this.categorias.stream()
        				.map(it->it.getNombre()).toList()).build();
    }
}

