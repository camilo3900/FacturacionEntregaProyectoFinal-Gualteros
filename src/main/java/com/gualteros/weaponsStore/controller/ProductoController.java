package com.gualteros.weaponsStore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.gualteros.weaponsStore.models.Producto;
import com.gualteros.weaponsStore.models.dto.ProductoDto;
import com.gualteros.weaponsStore.models.extra.ProductoCategoria;
import com.gualteros.weaponsStore.service.ProductoService;
import com.gualteros.weaponsStore.utils.BaseApiResponse;

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
	@GetMapping("/sorted")
	public ResponseEntity<List<ProductoDto>> getAllSorted(){
		return new ResponseEntity<>(productoService.getAllOrder(), HttpStatus.OK);
	}
	//Agregar un producto
	@PostMapping("/")
	public ResponseEntity<?> agregarProducto(@RequestBody Producto producto) {
		try {
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
	@PostMapping("/agregar/agregar-categoria")
    public ResponseEntity<?> agregarCategoria(@RequestBody ProductoCategoria productoCat){
       try{
            return new ResponseEntity<>(productoService.agregarProductoEnCategoria(productoCat.getIdProducto(), productoCat.getIdCategoria()), HttpStatus.OK);
       }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
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
	@GetMapping("/por-categoria/{id}")
	public ResponseEntity<List<ProductoDto>> listarPorCategoria(@PathVariable("id") Long catId){
		return new ResponseEntity<>(productoService.getAllByCategory(catId), HttpStatus.OK);
	}
	// actualizar producto
	@PutMapping("/actualizar/{id}")
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
