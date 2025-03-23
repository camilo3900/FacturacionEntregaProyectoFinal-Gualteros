package com.gualteros.weaponsStore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gualteros.weaponsStore.models.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {


	@Query("SELECT c FROM Cliente c ORDER BY c.nombre ASC")
	List<Cliente> getAllClientesSorted();
	@Query("SELECT c FROM Cliente c WHERE c.nombre LIKE :name")	
	List<Cliente> findByName(@Param("name") String nombreCliente);
	@Query(value = "SELECT * FROM clientes C WHERE C.dni = :dni LIMIT 1", nativeQuery = true)			
	Cliente findByDni(@Param("dni") String dni);
}
