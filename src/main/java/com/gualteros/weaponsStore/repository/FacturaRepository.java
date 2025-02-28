package com.gualteros.weaponsStore.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.gualteros.weaponsStore.models.Factura;

@Repository
public interface FacturaRepository extends JpaRepository<Factura, Long> {
  
	@Query("SELECT F FROM Factura F WHERE F.codigo = :cod")
	Factura findByNumFactura(@Param("cod")UUID numFactura);

}
