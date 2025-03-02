package com.gualteros.weaponsStore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gualteros.weaponsStore.models.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
	@Query("SELECT c FROM Cliente c WHERE c.nombre LIKE :name")	
	public List<Cliente> findByName(@Param("name") String nombreCliente);
}
