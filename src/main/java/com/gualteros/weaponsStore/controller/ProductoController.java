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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.*;
import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@RequestMapping("/productos")
@Tag(name = "Productos Controller", description = "Crud de los productos." )
public class ProductoController {
	
	private ProductoService productoService;
	
	public ProductoController(@Autowired ProductoService productoService) {
		this.productoService = productoService;
	}
	//Obtener todos los productos
	@GetMapping("")
	@Operation(summary = "Lista Productos", description = "Se obtiene el listado completo de productos de la DB.")
	@ApiResponse(responseCode = "200", description = "Productos obtenidos en la DB")
	public ResponseEntity<BaseApiResponse> getAll() {
		return ResponseEntity.ok().body(BaseApiResponse.success("Listado de productos obtenido!", productoService.getAll()));
	}
	@GetMapping("/sorted")
	@Operation(summary = "Lista Productos Ordenados", description = "Se obtiene el listado de productos de la DB ordenados alfabeticamente por nombre.")
	@ApiResponse(responseCode = "200", description = "Se obtuvo el listado ordenado.")
	public ResponseEntity<BaseApiResponse> getAllSorted(){
		return ResponseEntity.ok().body(BaseApiResponse.success("Productos ordenados ha sido encontrado!",productoService.getAllOrder()));
	}
	//Agregar un producto
	@PostMapping("/")
	@Operation(summary = "Guarda Producto", description = "Se guarda un producto nuevo en la DB.")
	@ApiResponses({
		@ApiResponse(responseCode = "201", description = "Producto creado con exito"),
        @ApiResponse(responseCode = "500", description = "No se pudo guardar el producto debido a errores en la validación")
	})
	public ResponseEntity<?> agregarProducto(@RequestBody Producto producto) {
		try {
		return new ResponseEntity<>(BaseApiResponse.success("Producto guardado", productoService.insert(producto)),  HttpStatus.CREATED);
	} catch (Exception e) {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
	}
		
	}
	//Agregar todos los productos
	@PostMapping("/todos")
	@Operation(summary = "Guarda todos", description = "Se guardan todos los productos en la DB.")
	@ApiResponses({
		@ApiResponse(responseCode = "201", description = "Productos creados con exito"),
        @ApiResponse(responseCode = "406", description = "No se pudieron guardar los productos debido a errores en la validación")
	})
	public ResponseEntity<?> agregarProductos(@RequestBody List<Producto> productoList) {
		try {
			productoService.insertAll(productoList);
			return new ResponseEntity<>
			(BaseApiResponse.success("Productos agregador con exito!", null), HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(BaseApiResponse.error(HttpStatus.valueOf(406), e.getMessage()));
		}
		
	}
	@PostMapping("/agregar-categoria")
	@Operation(summary = "Guarda Categoria en Producto", description = "Se agrega una categoria nueva a un producto.")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "Categoria agregada."),
		@ApiResponse(responseCode = "500", description = "No se pudo agregar la categoria debido a errores en la validación")
	})
    public ResponseEntity<?> agregarCategoria(@RequestBody ProductoCategoria productoCat){
       try{
            return new ResponseEntity<>(BaseApiResponse.success("Categoria agregada en producto.", productoService.agregarProductoEnCategoria(productoCat.getIdProducto(), productoCat.getIdCategoria())), HttpStatus.OK);
       }catch(Exception e){
            return ResponseEntity.internalServerError().body(BaseApiResponse.error(HttpStatus.valueOf(500), e.getMessage()));
       }
    }
	@GetMapping("/{id}")
	@Operation(summary = "Busca Producto", description = "Se busca un producto por id en la DB.")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "Producto encontrado" ),
		@ApiResponse(responseCode = "404", description = "Producto no encontrado" )
	})
	public ResponseEntity<BaseApiResponse> getProductoById(@PathVariable("id") Long idProducto) {
		try {
			return new ResponseEntity<>
		(BaseApiResponse.success("Se obtuvo producto de la DB.", productoService.getById(idProducto)), HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(BaseApiResponse.error(HttpStatus.valueOf(404), "No encontrado"));
		}
		
	}
	// obtener por nombre
	@GetMapping("/filtrar-nombre")
	@Operation(summary = "Busca Producto por nombre", description = "Se filtran productos de la DB por nombre.")
	@ApiResponse(responseCode = "200", description = "Producto filtrado por nombre!")
	public ResponseEntity<BaseApiResponse> obtenerPorNombre(@RequestParam String name) {
		return new ResponseEntity<>
		(BaseApiResponse.success(String.format("Producto %s obtenido!", name ), productoService.getByName(name)), HttpStatus.OK);
	}
	@GetMapping("/por-categoria/{id}")
	@Operation(summary = "Busca Productos por categoria", description = "Se filtran productos de la DB por categoria.")
	@ApiResponse(responseCode = "200", description = "Productos obtenidos por categoria.")
	public ResponseEntity<BaseApiResponse> listarPorCategoria(@PathVariable("id") Long catId){
		return new ResponseEntity<>(BaseApiResponse.success("Productos listados por categoria!", productoService.getAllByCategory(catId)), HttpStatus.OK);
	}
	// actualizar producto
	@PutMapping("/actualizar/{id}")
	@Operation(summary = "Actualiza Producto", description = "Se actualiza producto de la DB por id")
	@ApiResponse(responseCode = "200", description = "Producto actualizado")
	public ResponseEntity<BaseApiResponse> actualizarProducto(@RequestBody ProductoDto producto,
			@PathVariable("id") Long idProd) {
		return new ResponseEntity<>
		(BaseApiResponse.success("Producto actualizado!", productoService.update(producto, idProd)), HttpStatus.OK);
	}
	// eliminar producto
	@DeleteMapping("/eliminar/{id}")
	@Operation(summary = "Elimina Producto", description = "Se elimina un producto de la tabla productos.")
	@ApiResponse(responseCode = "200", description = "Producto eliminado")    
        
	public ResponseEntity<BaseApiResponse> eliminarProducto(@PathVariable("id") Long idProd) {
		productoService.delete(idProd);
		return new ResponseEntity<>
		(BaseApiResponse.success("Producto eliminado con exito!", null), HttpStatus.OK);
	}
	// eliminar todos los productos
	@DeleteMapping("/eliminar-todos")
	@Operation(summary = "Elimina todos", description = "Se elimina todo el listado de productos de la DB.")
	@ApiResponse(responseCode = "200", description = "Productos eliminados.")
	public ResponseEntity<BaseApiResponse> eliminarTodos() {
		productoService.deleteAll();
		return new ResponseEntity<>
		(BaseApiResponse.success("Productos eliminados con exito!", null), HttpStatus.OK);
	}
}
