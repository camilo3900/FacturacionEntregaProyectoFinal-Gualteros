package com.gualteros.weaponsStore.service;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gualteros.weaponsStore.models.*;
import com.gualteros.weaponsStore.models.dto.FacturaDto;
import com.gualteros.weaponsStore.models.extra.ItemDetail;
import com.gualteros.weaponsStore.models.request.FacturaRequest;
import com.gualteros.weaponsStore.repository.*;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class FacturaService   {
	
	private FacturaRepository facturaRepository;
	private ProductoRepository productoRepository;
	private ItemFacturaRepository itemFacturaRepository;
	private ClienteRepository clienteRep;

	public void insertAll(List<Factura> facturaList) {
		facturaList.forEach(it->facturaRepository.save(it));
	}

	public FacturaDto insert(FacturaRequest facturaNueva) {
		 List<ItemFactura> items = new ArrayList<>();
        Cliente cliente = clienteRep.findById(facturaNueva.getIdCliente())
       .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
       facturaNueva.getItemsRequest().stream().forEach(it->{
            Producto producto = productoRepository.findById(it.getJuegoId()).orElseThrow(() -> new RuntimeException("Juego no encontrado."));
            if(producto.getStock() < it.getCantidad()){
                throw new RuntimeException("No hay stock suficiente para la cantidad solicitada.");
            }
            ItemFactura nuevoItem = ItemFactura.builder().cantidad(it.getCantidad())
            .factura(new Factura()).itemDetail(ItemDetail.builder().productoId(producto.getId()).nombreProducto(producto.getNombre()).precioUnitario(producto.getPrecio()).build()).build();
            nuevoItem.setTotalItem(nuevoItem.calcularTotalPagarItem());
            items.add(nuevoItem);
            producto.vender(it.getCantidad());
            productoRepository.save(producto);
       });
       Factura nuevaFactura = Factura.builder()
       .fechaEmision(facturaNueva.getFecha()==null ? LocalDate.now():facturaNueva.getFecha()) 
       .totalPagar(0.0) 
       .items(items)
       .cliente(cliente)
       .build();
       // Asignar la factura a cada item antes de guardarla
       items.forEach(it -> it.setFactura(nuevaFactura));
       // Calcular total de la factura antes de guardarla
       nuevaFactura.calcularPago();
       // Guardar la factura con los items asociados
       return facturaRepository.save(nuevaFactura).toFacturaDto();
	}
	

	public List<FacturaDto> getAll() {
		return facturaRepository.findAll()
				.stream().map(it->it.toFacturaDto())
				.toList();
	}

	public FacturaDto getByCode(String codigo) {
		UUID codigoFactura = UUID.fromString(codigo);
        return facturaRepository.findById(codigoFactura).map(it-> it.toFacturaDto()).orElseThrow(()->new RuntimeException(String.format("No se encontró la factura con codigo: %s", codigo)));

	}

	public List<FacturaDto> getFacturasPorFecha() {
        return facturaRepository.orderByDate().stream().map(it->it.toFacturaDto()).toList();
    }



	public List<FacturaDto> getFacturasByCliente(Long idCliente) {
        return facturaRepository.getFacturasCliente(idCliente).stream().map(it->it.toFacturaDto()).toList();
    }
	public void addItem(String codigoFactura, ItemFactura nuevoItem ){
		Factura facturaEncontrada = facturaRepository.findById(UUID.fromString(codigoFactura)).orElseThrow(()-> new RuntimeException("Factura no encontrada."));
		facturaEncontrada.agregarItem(nuevoItem);
		facturaRepository.save(facturaEncontrada);

	}

	public void eliminarItem(String idFactura, Long idItem){
		Factura facturaEncontrada = facturaRepository.findById(UUID.fromString(idFactura)).orElseThrow(()-> new RuntimeException("Factura no encontrada."));
        ItemFactura itemEncontrado = itemFacturaRepository.findById(idItem).orElseThrow(()-> new RuntimeException("Item no existe."));
		facturaEncontrada.eliminarItem(itemEncontrado);
		facturaRepository.save(facturaEncontrada);
	}

	@Transactional
	public void deleteFactura(String codigo) {
		UUID codigoFactura = UUID.fromString(codigo);
        Factura facturaEncontrada = facturaRepository.findById(codigoFactura).orElse(null);
        if(facturaEncontrada == null){
            throw new RuntimeException(String.format("No se encontró la factura con codigo: %s", codigo));
        }
        facturaEncontrada.getItems().forEach(it->{
            itemFacturaRepository.delete(it);
        });
        facturaEncontrada.getCliente().getFacturas().remove(facturaEncontrada);
        facturaRepository.delete(facturaEncontrada);

	}

	public List<FacturaDto> obtenerPorPrecio(){
		return facturaRepository.getFacturasPrecioSorted()
                .stream().map((it)->it.toFacturaDto())
                .toList();
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
