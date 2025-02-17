package com.gualteros.weaponsStore.service;

import java.util.List;

public interface BaseEntityOp <T,K>{
    void insertAll(List<T> entityList);
    void insertObject(T entity);
    List<K> getAll();
    K getObjectById(Long id);
    void deleteAll();
    void deleteObject(Long id);
    void updateObject(T element, Long id);
}
