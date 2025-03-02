package com.gualteros.weaponsStore.service;


import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gualteros.weaponsStore.models.*;
import com.gualteros.weaponsStore.models.dto.FacturaDto;
import com.gualteros.weaponsStore.repository.*;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class FacturaService   {
	
	private FacturaRepository facturaRepository;
	private ProductoRepository productoRepository;
	private ClienteRepository clienteRep;

	public void insertAll(List<Factura> facturaList) {
		facturaList.forEach(it->insert(it));
	}

	public FacturaDto insert(Factura factura) {
		Cliente cliente = clienteRep.findById(factura.getCliente().getId())
		        .orElseThrow(() -> 
		        new RuntimeException(String.format("Cliente %s no encontrado!"
		        		, factura.getCliente().getId())));
		List<ItemFactura> items = new ArrayList<>();
		factura.getItems().stream().forEach(it->{
			if (it.getProducto() == null || it.getProducto().getId() == null) {
				throw new NoSuchElementException("El producto en el detalle de la "
						+ "factura debe tener un ID válido");	
			}
			Producto nuevoProducto = productoRepository.findById(it.getProducto().getId())
					.orElseThrow(()-> new NoSuchElementException());
			if (nuevoProducto.getStock() < it.getCantidad()) {
				throw new RuntimeException("No hay suficiente stock de " 
			+ nuevoProducto.getNombre());
			}
			ItemFactura nuevoItem = ItemFactura.builder()
					.cantidad(it.getCantidad())
					.producto(nuevoProducto).build();
			nuevoItem.setValorTotal(nuevoItem.calcularTotalItem());
			nuevoProducto.vender(it.getCantidad());
			items.add(nuevoItem);
			productoRepository.save(nuevoProducto);
		});	
		Factura nuevaFactura = Factura.builder()
				
				.fechaEmision(factura.getFechaEmision())
				.totalPagar(0.0)
				.cliente(cliente)
				.items(items)
				.build();
				
		items.forEach(it->it.setFactura(nuevaFactura));
		nuevaFactura.calcularPago();
			
		return facturaRepository.save(nuevaFactura).toFacturaDto();
	}
	

	public List<FacturaDto> getAll() {
		return facturaRepository.findAll()
				.stream().map(Factura::toFacturaDto)
				.toList();
	}

	public FacturaDto getByCode(String codigo) {
		return facturaRepository.findById(UUID.fromString(codigo))
				.stream().map(it->it.toFacturaDto())
				.findFirst()
				.orElseThrow();
	}

	public FacturaDto getByCodigo(String codigo) {
		try {
			UUID uuid = UUID.fromString(codigo);
			return facturaRepository.findByNumFactura(uuid)
					.toFacturaDto();
		} catch (Exception e) {
			throw new NoSuchElementException("No se encontró "
					+ "la factura con el código " + codigo);
		}   
	}
	// TODO falta actualizar factura


	public void deleteFactura(String codigo) {
		facturaRepository.deleteById(UUID.fromString(codigo));
	}

	
	public List<FacturaDto> ordenarPorFecha() {
		return facturaRepository.orderByDate()
				.stream().map((it)->it.toFacturaDto())
				.toList();
	}

	public void deleteAll() {
		facturaRepository.deleteAll();
		
	}

    


}
