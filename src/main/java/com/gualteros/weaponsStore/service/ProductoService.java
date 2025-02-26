package com.gualteros.weaponsStore.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gualteros.weaponsStore.models.Categoria;
import com.gualteros.weaponsStore.models.Producto;
import com.gualteros.weaponsStore.models.dto.ProductoDto;
import com.gualteros.weaponsStore.repository.CategoriaRepository;
import com.gualteros.weaponsStore.repository.ProductoRepository;

@Service
public class ProductoService implements BaseEntityOp<Producto, ProductoDto> {

	private ProductoRepository productoRepository;
	private CategoriaRepository categoriaRepository;

	public ProductoService(@Autowired ProductoRepository productoRepository
			,@Autowired CategoriaRepository categoriaRepository) {
		this.productoRepository = productoRepository;
		this.categoriaRepository = categoriaRepository;
	}

	@Override
	public void insertAll(List<Producto> productoList) {
		productoRepository.saveAll(productoList);

	}

	@Override
	public ProductoDto insert(Producto producto) {
		return productoRepository.save(producto).toProductoDto();
	}

	@Override
	public List<ProductoDto> getAll() {
		return productoRepository.findAll()
				.stream().map(it -> it.toProductoDto())
				.toList();
	}

	@Override
	public ProductoDto getById(Long id) {
		return productoRepository.findById(id)
				.map(it -> it.toProductoDto())
				.orElseThrow(NoSuchElementException::new);
	}

	@Override
	public ProductoDto update(ProductoDto productoDto, Long id) {
		Producto productoEncontrado = productoRepository.findById(id)
				.orElseThrow(NoSuchElementException::new);
		List<Categoria> categorias = new ArrayList<>();
		for (String c : productoDto.getCategoriasDto()) {
			Categoria idCat = categoriaRepository.findAll()
					.stream().filter(cat -> cat.getNombre().equals(c))
					.findFirst().orElse(null);
			if (idCat != null) {
				categorias.add(idCat);
			}
		}
		productoEncontrado.setNombre(productoDto.getNombreDto());
		productoEncontrado.setPrecio(productoDto.getPrecioDto());
		productoEncontrado.setStock(productoDto.getStockDto());
		productoEncontrado.setCategorias(categorias);
		return productoRepository.save(productoEncontrado).toProductoDto();

	}

	@Override
	public List<ProductoDto> getByName(String name) {
		return productoRepository.getProductoByNombre(name+"%")
				.stream().map(it -> it.toProductoDto()).toList();
	}

	@Override
	public void delete(Long id) {
		productoRepository.deleteById(id);

	}

	@Override
	public void deleteAll() {
		productoRepository.deleteAll();

	}

}
