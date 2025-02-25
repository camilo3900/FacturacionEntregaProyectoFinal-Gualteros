package com.gualteros.weaponsStore.models;

import java.util.List;

import com.gualteros.weaponsStore.models.dto.ProductoDto;
import com.gualteros.weaponsStore.models.extra.Categoria;

import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "productos")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Producto {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producto")
    private Long id;
    @Column(name = "nombre_producto")
    private String nombre;
    @Enumerated(EnumType.STRING)
    @Column(name = "categoria")
    private Categoria categoria;
    @Column(name = "precio")
    private Double precio;
    @Column(name = "stock")
    private Integer stock;
    @Transient
    private Integer cantidadComprada;
    @Nullable
    @ManyToMany(mappedBy = "productos", fetch = FetchType.EAGER)
    List<Factura> facturas;


    public ProductoDto toProductoDto() {
        return new ProductoDto(this.nombre, this.categoria, this.precio);
    }

}
