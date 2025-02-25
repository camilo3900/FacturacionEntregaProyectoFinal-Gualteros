package com.gualteros.weaponsStore.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gualteros.weaponsStore.models.Factura;
import com.gualteros.weaponsStore.models.dto.FacturaDto;
import com.gualteros.weaponsStore.repository.FacturaRepository;

@Service
public class FacturaService implements BaseEntityOp<Factura, FacturaDto> {

    //ATTIBUTOS
    private FacturaRepository facturaRepository;
    //INYECCION DE DEPENDENCIAS
    public FacturaService(@Autowired FacturaRepository facturaRepository) {
        this.facturaRepository = facturaRepository;
    }
    @Override
    public void insertAll(List<Factura> entityList) {
        try {
            facturaRepository.saveAll(entityList);
        } catch (Exception e) {
            throw new RuntimeException("Error al insertar: "+ e.getMessage());
        }
    }

    @Override
    public void insertObject(Factura entity) {
        try {
            facturaRepository.save(entity);
            System.err.println("factura guardada!");
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public List<FacturaDto> getAll() {
        return facturaRepository.findAll().stream().map(it-> it.toFacturaDto()).toList();
    }

    @Override
    public FacturaDto getObjectById(Long id) {
        return facturaRepository.findById(id).map(it-> it.toFacturaDto()).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public void deleteAll() {
        facturaRepository.deleteAll();
    }

    @Override
    public void deleteObject(Long id) {
        facturaRepository.deleteById(id);
    }

    @Override
    public void updateObject(Factura element, Long id) {
        Factura factura = facturaRepository.findById(id).orElseThrow(NoSuchElementException::new);
        if (element.getNumeroFactura() != null) {
            factura.setNumeroFactura(element.getNumeroFactura());
        }
        if (element.getTotalPagar() != null) {
            factura.setTotalPagar(element.getTotalPagar());
        }
        
        try {
            facturaRepository.save(factura);
            System.out.printf("FACTURA: %s ACTUALIZADA!!", factura.getNumeroFactura());
        } catch (Exception e) {
            throw new RuntimeException("ERROR AL ACTUALIZAR: " + e);
        }
    }


}
