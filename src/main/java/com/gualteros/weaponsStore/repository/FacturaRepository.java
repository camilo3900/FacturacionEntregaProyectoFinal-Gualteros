package com.gualteros.weaponsStore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gualteros.weaponsStore.models.Factura;


@Repository
public interface FacturaRepository extends JpaRepository<Factura, Long> {

}
