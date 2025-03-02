package com.gualteros.weaponsStore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException.InternalServerError;

import com.gualteros.weaponsStore.models.Producto;
import com.gualteros.weaponsStore.models.dto.ProductoDto;
import com.gualteros.weaponsStore.service.ProductoService;

@RestController
@RequestMapping("/productos")
public class ProductoController {
	
	private ProductoService productoService;
	
	public ProductoController(@Autowired ProductoService productoService) {
		this.productoService = productoService;
	}
	//Obtener todos los productos
	@GetMapping
	public ResponseEntity<List<ProductoDto>> getAll() {
		return new ResponseEntity<>
		(productoService.getAll(), HttpStatus.OK);
	}
	//Agregar un producto
	@PostMapping("/")
	public ResponseEntity<?> agregarProducto(@RequestBody Producto producto) {try {
		return new ResponseEntity<>
		(productoService.insert(producto), HttpStatus.OK);
	} catch (Exception e) {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
	}
		
	}
	//Agregar todos los productos
	@PostMapping("/all")
	public ResponseEntity<String> agregarProductos(@RequestBody List<Producto> productoList) {
		try {
			productoService.insertAll(productoList);
			return new ResponseEntity<>
			("Productos agregador con exito!", HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(e.getMessage());
		}
		
	}
	@GetMapping("/{id}")
	public ResponseEntity<ProductoDto> getProductoById(@PathVariable("id") Long idProducto) {
		return new ResponseEntity<>
		(productoService.getById(idProducto), HttpStatus.OK);
	}
	// obtener por nombre
	@GetMapping("/filtrar-nombre")
	public ResponseEntity<List<ProductoDto>> obtenerPorNombre(@RequestParam String name) {
		return new ResponseEntity<>
		(productoService.getByName(name), HttpStatus.OK);
	}
	// actualizar producto
	@PutMapping("/{id}")
	public ResponseEntity<ProductoDto> actualizarProducto(@RequestBody ProductoDto producto,
			@PathVariable("id") Long idProd) {
		return new ResponseEntity<>
		(productoService.update(producto, idProd), HttpStatus.OK);
	}
	// eliminar producto
	@DeleteMapping("/{id}")
	public ResponseEntity<String> eliminarProducto(@PathVariable("id") Long idProd) {
		productoService.delete(idProd);
		return new ResponseEntity<>
		("Producto eliminado con exito!", HttpStatus.OK);
	}
	// eliminar todos los productos
	@DeleteMapping("/eliminar-todos")
	public ResponseEntity<String> eliminarTodos() {
		productoService.deleteAll();
		return new ResponseEntity<>
		("Productos eliminados con exito!", HttpStatus.OK);
	}
}
