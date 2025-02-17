package com.gualteros.weaponsStore.service;

import java.util.List;
import java.util.NoSuchElementException;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gualteros.weaponsStore.models.Comprador;
import com.gualteros.weaponsStore.models.dto.CompradorDto;
import com.gualteros.weaponsStore.repository.CompradorRepository;

@Service
public class CompradorService implements BaseEntityOp<Comprador, CompradorDto> {

    private CompradorRepository compradorRepository;

    /* INYECCION DE DEPENDENCIAS */
    public CompradorService(@Autowired CompradorRepository compradorRepository) {
        this.compradorRepository = compradorRepository;
    }


    // SE AGREGAN TODOS LOS COMPRADORES
    @Override
    public void insertAll(List<Comprador> entityList) {
        try {
            compradorRepository.saveAll(entityList);
        } catch (Exception e) {
            throw new RuntimeException("ERROR AL INSERTAR: " + e);
        }

    }

    // AGREGA UN COMPRADOR
    @Override
    public void insertObject(Comprador entity) {
        try {
            compradorRepository.save(entity);
            System.err.println("comprador agregado!");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    // OBTIENE TODOS LOS COMPRADORES DE LA DB
    @Override
    public List<CompradorDto> getAll() {
        return compradorRepository.findAll().stream().map(it -> it.toCompradorDto()).toList();
    }

    // OBTIENE UN COMPRADOR POR ID
    @Override
    public CompradorDto getObjectById(Long id) {

        return compradorRepository.findById(id).map(it -> it.toCompradorDto()).orElseThrow(NoSuchElementException::new);

        /*
         * return
         * compradorRepository.findById(id).map(it->it.toCompradorDto()).orElseThrow(
         * NoSuchElementException::new);
         */
    }

    // ELIMINA TODOS LOS COMPRADORES DE LA DB
    @Override
    public void deleteAll() {
        compradorRepository.deleteAll();
    }

    // ELIMINAMOS UN COMPRADOR POR ID
    @Override
    public void deleteObject(Long id) {
        compradorRepository.deleteById(id);
    }

    // SE ACTUALIZA UN COMPRADOR POR ID
    @Override
    public void updateObject(Comprador element, Long id) {
        Comprador comprador = compradorRepository.findById(id).orElseThrow(NoSuchElementException::new);
        if (element.getNombre() != null) {
            comprador.setNombre(element.getNombre());
        }
        if (element.getApellido() != null) {
            comprador.setApellido(element.getApellido());
        }
        if (element.getDni() != null) {
            comprador.setDni(element.getDni());
        }
        if (element.getDatos() != null) {
            comprador.setDatos(element.getDatos());
        }
        try {
            compradorRepository.save(comprador);
            System.out.printf("COMPRADOR: %s ACTUALIZADO!!", comprador.getNombre());
        } catch (Exception e) {
            throw new RuntimeException("ERROR AL ACTUALIZAR: " + e);
        }

    }

}
