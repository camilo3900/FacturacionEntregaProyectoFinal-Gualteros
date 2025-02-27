package com.gualteros.weaponsStore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gualteros.weaponsStore.models.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
	
    @Query("Select C from Categoria C WHERE C.nombre LIKE :nombreCat")
	public List<Categoria> getCategoriaByNombre(@Param("nombreCat") String nombre);
}
