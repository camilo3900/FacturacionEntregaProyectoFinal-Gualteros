package com.gualteros.weaponsStore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.gualteros.weaponsStore.models.Comprador;

@Repository
public interface CompradorRepository extends JpaRepository<Comprador, Long> {

}
