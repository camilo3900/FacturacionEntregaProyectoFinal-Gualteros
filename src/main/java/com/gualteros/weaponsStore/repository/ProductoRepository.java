package com.gualteros.weaponsStore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.gualteros.weaponsStore.models.Producto;


@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {

	@Query("SELECT P FROM Producto P JOIN P.categorias C WHERE C.id =:id")
	List<Producto> findProductosByCategorias(@Param("id") Long id);

	@Query("Select P from Producto P WHERE P.nombre LIKE :nombreProd")
	public List<Producto> getProductoByNombre(@Param("nombreProd") String nombre);

	@Query("SELECT P FROM Producto P ORDER BY nombre ASC")
	List<Producto> getProductosByName();

	@Modifying
	@Query(value= "INSERT INTO producto_categoria (producto_id, categoria_id) VALUES(:p,:c)", nativeQuery = true)
	void insertarProductoCategoria(@Param("p") Long producto, @Param("c") Long categoria);

}
