package com.gualteros.weaponsStore.models;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Table(name = "facturas")
@Data
@AllArgsConstructor
public class Factura {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @JsonFormat(shape = JsonFormat.Shape.STRING) 
    @Column(name = "codigo_venta")
    UUID codigo;
    @Column(name="fecha_emision")
    private LocalDate fechaEmision;
    @Column(name="pagar")
    Double totalPagar;
  


	public Factura() {
	}


}
