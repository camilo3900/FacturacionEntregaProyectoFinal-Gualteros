package com.gualteros.weaponsStore.models;

import java.util.List;
import java.util.UUID;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_factura")
    private Long id;
    @Column(name="numero_factura")
    private UUID numeroFactura; 
    @Column(name="total_pagar")
    private Double totalPagar;
    @ManyToOne(optional = false)
    @JoinColumn(name="cliente_id")
    private Comprador clienteId;
    @ManyToMany(fetch = FetchType.EAGER)
    @Nullable
    @JoinTable(name="factura_producto", joinColumns=  @JoinColumn(name = "factura_id" )
    , inverseJoinColumns = @JoinColumn(name = "producto_id"))
    private List<Producto> productos;


	public Factura() {
	}


}
