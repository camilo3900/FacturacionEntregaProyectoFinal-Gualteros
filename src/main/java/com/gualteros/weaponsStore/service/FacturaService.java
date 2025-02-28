package com.gualteros.weaponsStore.service;


import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gualteros.weaponsStore.models.Factura;
import com.gualteros.weaponsStore.models.ItemFactura;
import com.gualteros.weaponsStore.models.Producto;
import com.gualteros.weaponsStore.models.dto.FacturaDto;
import com.gualteros.weaponsStore.repository.FacturaRepository;
import com.gualteros.weaponsStore.repository.ProductoRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class FacturaService implements BaseEntityOp<Factura, FacturaDto>  {
	
	private FacturaRepository facturaRepository;
	private ProductoRepository productoRepository;
	
	@Override
	public void insertAll(List<Factura> facturaList) {
		facturaRepository.saveAll(facturaList);
		facturaList.forEach(it->insert(it));
		
		
	}

	@Override
	public FacturaDto insert(Factura factura) {
		List<ItemFactura> items = new ArrayList<>();
		factura.getItems().stream().forEach(it->{
			if (it.getProducto() == null || it.getProducto().getId() == null) {
				throw new NoSuchElementException("El producto en el detalle de la "
						+ "factura debe tener un ID válido");
				
			}
			Producto nuevoProducto = productoRepository.findById(it.getProducto().getId())
					.orElseThrow(()-> new NoSuchElementException());
			if (nuevoProducto.getStock() < it.getCantidad()) {
				throw new RuntimeException("No hay suficiente stock de " + nuevoProducto.getNombre());
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
				.items(items)
				.build();
		
		items.forEach(it->it.setFactura(nuevaFactura));
		nuevaFactura.calcularPago();
	
	
			
		return facturaRepository.save(nuevaFactura).toFacturaDto();
	}
	
//	public Factura crearFactura(Factura factura) {
//	}

	@Override
	public List<FacturaDto> getAll() {
		return facturaRepository.findAll()
				.stream().map(Factura::toFacturaDto)
				.toList();
	}

	@Override
	public FacturaDto getById(Long id) {
		return facturaRepository.findById(id).stream().map(it->it.toFacturaDto())
				.findFirst()
				.orElseThrow();
	}

	public FacturaDto getByCodigo(String codigo) {
		try {
			UUID uuid = UUID.fromString(codigo);
			return facturaRepository.findByNumFactura(uuid)
					.toFacturaDto();
		} catch (Exception e) {
			throw new NoSuchElementException("No se encontró la factura con el código " + codigo);
		}   
	}
	@Override
	public FacturaDto update(FacturaDto facturaDto, Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<FacturaDto> getByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Long id) {
		facturaRepository.deleteById(id);
		
	}

	@Override
	public void deleteAll() {
		facturaRepository.deleteAll();
		
	}

    


}
