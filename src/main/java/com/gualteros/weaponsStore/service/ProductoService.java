package com.gualteros.weaponsStore.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gualteros.weaponsStore.models.Producto;
import com.gualteros.weaponsStore.models.dto.ProductoDto;
import com.gualteros.weaponsStore.repository.ProductoRepository;

@Service
public class ProductoService implements BaseEntityOp<Producto, ProductoDto> {

    private ProductoRepository productoRepository;
    
    public ProductoService(@Autowired ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }
    //INSERTA TODOS LOS PRODUCTOS
    @Override
    public void insertAll(List<Producto> entityList) {
        try {
            productoRepository.saveAll(entityList);
        } catch (Exception e) {
            throw new RuntimeException("Error al insertar: "+ e.getMessage());
        }
    }
    //INSERTA UN PRODUCTO
    @Override
    public void insertObject(Producto entity) {
        try {
            productoRepository.save(entity);
            System.err.println("producto guardado!");
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
    //OBTENER TODOS LOS PRODUCTOS DE LA DB
    @Override
    public List<ProductoDto> getAll() {
        return productoRepository.findAll().stream().map(it-> it.toProductoDto()).toList();
    }
    //OBTENER UN PRODUCTO POR ID
    @Override
    public ProductoDto getObjectById(Long id) {
        return productoRepository.findById(id).map(it-> it.toProductoDto()).orElseThrow(NoSuchElementException::new);
    }
    //ELIMINAR TODO DE LA TABLA PRODUCTOS
    @Override
    public void deleteAll() {
        productoRepository.deleteAll();
    }

    @Override
    public void deleteObject(Long id) {
        productoRepository.deleteById(id);
    }
    //ATUALIZA UN REGISTRO DE LA TABLA PRODUCTOS    
    @Override
    public void updateObject(Producto element, Long id) {
        Producto producto = productoRepository.findById(id).orElseThrow(NoSuchElementException::new);
        if (element.getNombre() != null) {
            producto.setNombre(element.getNombre());
        }
        if (element.getCategoria() != null) {
            producto.setCategoria(element.getCategoria());
        }
        if (element.getPrecio() != null) {
            producto.setPrecio(element.getPrecio());
        }
        if (element.getStock() != null) {
            producto.setStock(element.getStock());
        }
        try {
            productoRepository.save(producto);
            System.out.printf("PRODUCTO: %s ACTUALIZADO!!", producto.getNombre());
        } catch (Exception e) {
            throw new RuntimeException("ERROR AL ACTUALIZAR: " + e);
        }
    }

}
