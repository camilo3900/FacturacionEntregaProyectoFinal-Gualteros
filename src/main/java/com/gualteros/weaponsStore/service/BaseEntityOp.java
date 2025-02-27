package com.gualteros.weaponsStore.service;

import java.util.List;

/* Esta interfaz generica tiene las operaciones basicas de
CRUD de las tablas.
T- Representa la entidad definida (Producto)
K- Representa el dto cuando se hacen los select (ProductoDto)
 */
public interface BaseEntityOp <T,K>{
	
    void insertAll(List<T> entityList);
    K insert(T entity);
    List<K> getAll();
    K getById(Long id);
    K update(K entity, Long id);
    List<K> getByName(String name);
    void delete(Long id);
    void deleteAll();

}
