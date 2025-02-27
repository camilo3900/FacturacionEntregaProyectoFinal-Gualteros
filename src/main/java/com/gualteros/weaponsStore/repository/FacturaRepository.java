package com.gualteros.weaponsStore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.gualteros.weaponsStore.models.Factura;

@Repository
public interface FacturaRepository extends JpaRepository<Factura, Long> {
    /* TODO me falta agregar esas consultas avanzadas, en el 
     * repositorio de github se enocontrará vigente el proyecto
     */
   /*  @Query("SELECT F.numeroFactura, C.nombre, F.totalPagar FROM Alumno A JOIN F.cliente C WHERE F.clienteId = :idComprador")
    List<Object[]> findFacturasCliente(@Param("idComprador") Long id); */

}
