package com.gualteros.weaponsStore.models;

import com.gualteros.weaponsStore.models.dto.ProductoDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Table(name = "productos")
@Data
@AllArgsConstructor
public class Producto {

    static Integer cantidad;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producto")
    private Long id;
    @Column(name = "nombre_producto")
    private String nombre;
    @Column(name = "categoria")
    private Categoria categoria;
    @Column(name = "precio")
    private Double precio;
    @Column(name = "stock")
    private Integer stock;

    public ProductoDto toProductoDto() {
        return new ProductoDto(this.nombre, this.categoria, this.precio);
    }

}
