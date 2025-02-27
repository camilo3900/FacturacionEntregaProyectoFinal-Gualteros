package com.gualteros.weaponsStore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
	public ResponseEntity<ProductoDto> agregarProducto(@RequestBody Producto producto) {
		return new ResponseEntity<>
		(productoService.insert(producto), HttpStatus.OK);
	}
	//Agregar todos los productos
	@PostMapping("/all")
	public ResponseEntity<String> agregarProductos(@RequestBody List<Producto> productoList) {
		productoService.insertAll(productoList);
		return new ResponseEntity<>
		("Productos agregador con exito!", HttpStatus.OK);
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
