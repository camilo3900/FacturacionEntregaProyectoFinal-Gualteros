package com.gualteros.weaponsStore.controller;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.gualteros.weaponsStore.models.Categoria;
import com.gualteros.weaponsStore.models.dto.CategoriaDto;
import com.gualteros.weaponsStore.service.CategoriaService;
import com.gualteros.weaponsStore.utils.BaseApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/categorias")
@Tag(name = "Categorias Controller", description = "Crud de las categorias.")
public class CategoriaController {

	private CategoriaService categoriaService;
	//INYECCION DE DEPENDENCIAS
	public CategoriaController(@Autowired CategoriaService categoriaService) {
		this.categoriaService = categoriaService;
	}
	//LISTAR TODAS LAS CATEGORIAS
	@GetMapping("/todos")
	@Operation(summary = "Lista Categorias", description = "Se obtiene todo el listado de categorias de la DB.")
	@ApiResponse(responseCode = "200", description = "Categorias obtenidas de la DB")
	public ResponseEntity<?> getAll() {

		return ResponseEntity.ok(BaseApiResponse.success("Opetacion exitosa!", categoriaService.getAll()));
	}
	//GUARDA UNA CATEGORIA
	@PostMapping("/")
	@Operation(summary = "Guardar Categoria.", description = "Se guarda una categorias en la DB.")
	@ApiResponses({
		@ApiResponse(responseCode  ="201", description = "La categoria ha sido correctamente agregada."),
		@ApiResponse(responseCode ="500", description = "Hubo un error en el server.")
	})
	public ResponseEntity<?> agregarCategoria(@RequestBody Categoria categoria){
		try {
			return new ResponseEntity<>(BaseApiResponse.success("Categoria Creada!", categoriaService.insert(categoria)), HttpStatus.CREATED);
		} catch (Exception e) {
			return ResponseEntity
					.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(BaseApiResponse.error(HttpStatus.valueOf(500), e.getMessage() ));
		}

	}
	//GUARDA TODAS
	@PostMapping("/agregar-todos")
	@Operation(summary = "Guardar todos", description = "Se guardan un listado de categorias en la DB.")
	@ApiResponses({
        @ApiResponse(responseCode  ="201", description = "Las categorias han sido correctamente agregadas."),
        @ApiResponse(responseCode ="404", description = "Hubo un error en el server.")
    })
	public ResponseEntity<?> agregarCategorias(@RequestBody List<Categoria> categorias) {
		try {
			categoriaService.insertAll(categorias);
			return new ResponseEntity<>(BaseApiResponse.success("Categorias agregadas", null), HttpStatus.CREATED);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(BaseApiResponse.error(HttpStatus.valueOf(404), e.getMessage()));
		}

	}
	//OBTIENE UNA CATEGORIA POR ID
	@GetMapping("/encontrar/{idCategoria}")
	@Operation(summary = "Buscar categoria", description = "Se busca una categoria por id en la DB.")
	@ApiResponse(responseCode = "200", description = "Categorias encontrada")
	public ResponseEntity<BaseApiResponse> getCategoriaById(@PathVariable("idCategoria")Long id) {
		return ResponseEntity.ok().body(BaseApiResponse.success("Categoria Encontrada!", categoriaService.getById(id)));
	}
	//LISTA TODOS EN ORDEN ALFABETICO
	@GetMapping("/todos/sorted")
	@Operation(summary = "Buscar listado de categorias ordenado", description = "Se busca el listado de categorias en la DB y se ordena alfabeticamente por nombre.")
	@ApiResponse(responseCode = "200", description = "Categorias listadas correctamente.")
	public ResponseEntity<BaseApiResponse> getAllSorted() {
        return new ResponseEntity<>(BaseApiResponse.success("Operacion Exitosa!", categoriaService.getAllOrder()), HttpStatus.OK);
    }
	//FILTRA CATEGORIAS POR NOMBRE
	@GetMapping("/encontrar/por-nombre")
	@Operation(summary = "Buscar categoria por nombre", description = "Se filtra las categorias filtradas por nombre.")
	@ApiResponse(responseCode = "200", description = "Categorias filtradas por nombre")
	public ResponseEntity<BaseApiResponse> obtenerPorNombre(@RequestParam String name) {
		return ResponseEntity.ok().body(BaseApiResponse.success("Busqueda exitosa!", categoriaService.getByName(name)));
	}
	//ACTUALIZA UNA CATEGORIA
	@PutMapping("/actualizar/{id}")
	@Operation(summary = "Actualizar Categoria", description = "Se actualiza una categoria de DB.")
	@ApiResponse(responseCode = "200", description = "Categoria actualizada y guardada!")
	public ResponseEntity<BaseApiResponse> actualizarCategoria(@RequestBody CategoriaDto categoria, @PathVariable("id") Long idCat) {
		return ResponseEntity.ok().body(BaseApiResponse.success("Operacion exitosa!", categoriaService.update(categoria, idCat)));
	}
	
	//ELIMINA UNA CATEGORIA
	@DeleteMapping("/eliminar/{id}")
	@Operation(summary = "Elimina Categoria", description = "Se elimina por id una categoria de la DB.")
	@ApiResponse(responseCode = "200", description = "Categoria se pudo eliminar")
	public ResponseEntity<BaseApiResponse> deleteCategoria(@PathVariable("id") Long idCat) {
		categoriaService.delete(idCat);
		return ResponseEntity.ok().body(BaseApiResponse.success("Categoria Eliminada!", null));
	}
	//ELIMINA TODAS LAS CATEGORIAS
	@DeleteMapping("/eliminar-todo")
	@Operation(summary = "Eliminar todas las categorias", description = "Se elimina todos los registros de la tabla categorias.")
	@ApiResponses({
		@ApiResponse(responseCode  ="200", description = "Todas las categorias han sido correctamente eliminadas."),
        @ApiResponse(responseCode ="500", description = "Hubo un error en el server.")
    })
	public ResponseEntity<?> deleteAll() {

		try {
			categoriaService.deleteAll();
		return ResponseEntity.status(HttpStatus.OK).body(BaseApiResponse.success("Categorias Eliminadas", null));
		} catch (Exception e) {
			return new ResponseEntity<>(BaseApiResponse.error(HttpStatus.valueOf(500), e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	
}
