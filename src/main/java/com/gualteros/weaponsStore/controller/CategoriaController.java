package com.gualteros.weaponsStore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.gualteros.weaponsStore.models.Categoria;
import com.gualteros.weaponsStore.models.dto.CategoriaDto;
import com.gualteros.weaponsStore.service.CategoriaService;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

	private CategoriaService categoriaService;
	//inyeccion de dependencias
	public CategoriaController(@Autowired CategoriaService categoriaService) {
		this.categoriaService = categoriaService;
	}
	//obtener todas las categorias
	@GetMapping
	public List<CategoriaDto> getAll() {
		return categoriaService.getAll();
	}
	//Agregar una categoria nueva
	@PostMapping("/")
	public ResponseEntity<?> agregarCategoria(@RequestBody Categoria categoria){
		try {
			return new ResponseEntity<>(categoriaService.insert(categoria)
					, HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity
					.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(e.getMessage());
		}

	}
	//Agregar varias categorias
	@PostMapping("/all")
	public ResponseEntity<String> agregarCategorias(@RequestBody List<Categoria> categorias) {
		
		try {
			categoriaService.insertAll(categorias);
			return new ResponseEntity<>("Categorias agregadas", HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}

	}
	//Obtener una categoria por id
	@GetMapping("/{idCategoria}")
	public ResponseEntity<CategoriaDto> getCategoriaById(@PathVariable("idCategoria")Long id) {
		return new ResponseEntity<>(categoriaService.getById(id), HttpStatus.OK);
	}
	//Obtener una categoria por nombre
	@GetMapping("/filtrar-nombre")
	public ResponseEntity<List<CategoriaDto>> obtenerPorNombre(@RequestParam String name) {
		return new ResponseEntity<>(categoriaService.getByName(name), HttpStatus.OK);
	}
	//Actualizar una categoria
	@PutMapping("/{id}")
	public ResponseEntity<CategoriaDto> actualizarCategoria(@RequestBody CategoriaDto categoria,
			@PathVariable("id") Long idCat) {
		return new ResponseEntity<>(categoriaService.update(categoria, idCat), HttpStatus.OK);
	}
	
	//Eliminar categoria
	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<String> deleteCategoria(@PathVariable("id") Long idCat) {
		categoriaService.delete(idCat);
		return new ResponseEntity<>("Categoria eliminada!", HttpStatus.OK);
	}
	//Eliminar todas las categorias
	@DeleteMapping("/eliminar/all")
	public ResponseEntity<String> deleteAll() {
		categoriaService.deleteAll();
		return new ResponseEntity<>("Categorias eliminadas!", HttpStatus.OK);
	}
	
	
}
