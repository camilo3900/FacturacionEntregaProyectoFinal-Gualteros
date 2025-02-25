package com.gualteros.weaponsStore.service;

import java.util.List;

/* Esta interfaz generica tiene las operaciones basicas de
CRUD de las tablas.
T- Representa la entidad definida (Producto)
K- Representa el dto cuando se hacen los select (ProductoDto)
 */
public interface BaseEntityOp <T,K>{
    void insertAll(List<T> entityList);
    void insertObject(T entity);
    List<K> getAll();
    K getObjectById(Long id);
    void deleteAll();
    void deleteObject(Long id);
    void updateObject(T element, Long id);
}
