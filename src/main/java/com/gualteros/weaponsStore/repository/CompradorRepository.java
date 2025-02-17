package com.gualteros.weaponsStore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gualteros.weaponsStore.models.Comprador;
import com.gualteros.weaponsStore.models.dto.CompradorDto;

@Repository
public interface CompradorRepository extends JpaRepository<Comprador, Long> {

    @Query("SELECT * FROM Comprador WHERE apellido = : apellidoParam")
    List<CompradorDto> getCompradorByApellido(@Param("apellidoParam") String apellido);
}
