package com.gualteros.weaponsStore.service;

import java.util.List;


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
