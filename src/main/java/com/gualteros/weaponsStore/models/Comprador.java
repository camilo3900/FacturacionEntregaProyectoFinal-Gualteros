package com.gualteros.weaponsStore.models;

import com.gualteros.weaponsStore.models.dto.CompradorDto;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "compradores")
public class Comprador  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_comprador")
    private Long id;
    @Column(name = "dni")
    private Long dni;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "apellido")
    private String apellido;
    @Embedded
    @Column(name = "datos")
    private Datos datos;

    //type conversion
    public CompradorDto toCompradorDto(){
        return new CompradorDto(this.nombre, this.apellido);
    }

}
