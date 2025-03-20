package com.gualteros.weaponsStore.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gualteros.weaponsStore.models.Categoria;
import com.gualteros.weaponsStore.models.Producto;
import com.gualteros.weaponsStore.models.dto.ProductoDto;
import com.gualteros.weaponsStore.repository.CategoriaRepository;
import com.gualteros.weaponsStore.repository.ProductoRepository;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ProductoService implements BaseEntityOp<Producto, ProductoDto> {

	private ProductoRepository productoRepository;
	private CategoriaRepository categoriaRepository;
	

	@Override
	public void insertAll(List<Producto> productoList) {
		productoList.stream().forEach(it->{
			validateProducto(it);
			productoRepository.save(it);
		});
		
	}
	public Producto validateProducto(Producto prod) {
		Producto productoEncontrado = productoRepository
				.findAll().stream()
				.filter(it -> it.getNombre().contains(prod.getNombre()))
				.findFirst().orElse(null);
				if(productoEncontrado!=null) {
					throw new RuntimeException("PRODUCTOS REPETIDOS!");
				}
				return prod;
	}

	@Override
	public ProductoDto insert(Producto productoNuevo) {
		//validation category exist
		List<Categoria> categorias = new ArrayList<>();
		productoRepository.findAll().stream().forEach(it->{
			if(it.getNombre().contains(productoNuevo.getNombre())){
				throw new RuntimeException("El nombre del producto ya existe");
			};
		});
		for(Categoria c : categoriaRepository.findAll()){
			Categoria idCat = productoNuevo.getCategorias().stream().filter(cat->cat.getId().equals(c.getId())).findFirst().orElse(null);
			if(idCat != null){
				categorias.add(idCat);
			}
		}
		Producto producto = Producto.builder().nombre(productoNuevo.getNombre())
		.precio(productoNuevo.getPrecio())
		.categorias(categorias)
		.stock(productoNuevo.getStock())
		.build();
		return productoRepository.save(producto).toProductoDto();
	}
	@Transactional
	public ProductoDto agregarProductoEnCategoria(Long productoId, Long categoriaId){
		Categoria categoriaEncontrada = categoriaRepository.findById(categoriaId)
		.orElseThrow(()-> new RuntimeException("Categoria no encontrada!"));
		Producto productoEncontrado = productoRepository.findById(productoId).orElseThrow(()-> new RuntimeException("Producto no encontrado!"));
		productoEncontrado.agregarCategoria(categoriaEncontrada);
		productoRepository.insertarProductoCategoria(productoEncontrado.getId(), categoriaEncontrada.getId());
		return productoEncontrado.toProductoDto();
	}
	@Override
	public List<ProductoDto> getAll() {
		return productoRepository.findAll()
				.stream().map(it -> it.toProductoDto())
				.toList();
	}

	@Override
	public ProductoDto getById(Long id) {
		Producto productoEncontrado = productoRepository.findById(id).orElseThrow(()->new RuntimeException("Juego no encontrado!"));
		return productoEncontrado.toProductoDto();
				
	}

	public List<ProductoDto> getAllOrder(){
		return productoRepository.getProductosByName().stream()
		.map(it->it.toProductoDto())
		.toList();

	}

	public List<ProductoDto> getAllByCategory(Long catId){
		return productoRepository.findProductosByCategorias(catId).stream()
		.map(it->it.toProductoDto())
		.toList();
	}

	@Override
	public ProductoDto update(ProductoDto productoActualizar, Long id) {
		//validacion existencia en la DB
        Producto productoEncontrado = productoRepository.findById(id)
        .orElseThrow(()-> new RuntimeException("Producto no existe!"));
        productoEncontrado.actualizarProducto(productoActualizar);
        return productoRepository.save(productoEncontrado).toProductoDto();


	}

	@Override
	public List<ProductoDto> getByName(String name) {
		return productoRepository.getProductoByNombre(name+"%")
				.stream().map(it -> it.toProductoDto()).toList();
	}

	@Override
	@Transactional
	public void delete(Long id) {
		Producto productoEncontrado = productoRepository.findById(id).orElseThrow(()->new RuntimeException("Producto no encontrado!"));
		productoEncontrado.getCategorias().forEach(it->{
			it.getProductos().remove(productoEncontrado);
		});
		productoRepository.deleteById(id);
		

	}

	@Override
	@Transactional
	public void deleteAll() {
		productoRepository.deleteAll();

	}

}
