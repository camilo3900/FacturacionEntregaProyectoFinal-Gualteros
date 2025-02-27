package com.gualteros.weaponsStore.service;

import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gualteros.weaponsStore.models.Categoria;
import com.gualteros.weaponsStore.models.dto.CategoriaDto;
import com.gualteros.weaponsStore.repository.CategoriaRepository;

@Service
public class CategoriaService implements BaseEntityOp<Categoria, CategoriaDto> {
	@Autowired
	private CategoriaRepository categoriaRepository;

	@Override
	public void insertAll(List<Categoria> categoriaList) {
		categoriaRepository.saveAll(categoriaList);

	}

	@Override
	public CategoriaDto insert(Categoria categoria) {
		try {
			Categoria categoriaEncontrada = categoriaRepository.findAll().stream()
					.filter(it -> it.getNombre().contains(categoria.getNombre()))
					.findFirst()
					.orElse(null);
			if (categoriaEncontrada != null) {
				System.out.println("CATEGORIA YA EXISTE!");
				return null;
			}
			return categoriaRepository.save(categoria).toCategoriaDto();
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	@Override
	public List<CategoriaDto> getAll() {
		return categoriaRepository.findAll().stream()
				.map(it -> it.toCategoriaDto())
				.toList();
	}

	@Override
	public CategoriaDto getById(Long idCategoria) {
		return categoriaRepository.findById(idCategoria)
				.map(it -> it.toCategoriaDto())
				.orElseThrow(NoSuchElementException::new);
	}

	@Override
	public CategoriaDto update(CategoriaDto categoriaDto, Long id) {
		Categoria c = categoriaRepository.findById(id)
				.orElseThrow(NoSuchElementException::new);
		if (categoriaDto.getNombreDto() != null) {
			c.setNombre(categoriaDto.getNombreDto());
		}
		if (categoriaDto.getDescDto() != null) {
			c.setDesc(categoriaDto.getDescDto());
		}

		return categoriaRepository.save(c).toCategoriaDto();
	}

	@Override
	public List<CategoriaDto> getByName(String name) {
		return categoriaRepository.getCategoriaByNombre(name + "%")
				.stream().map(it -> it.toCategoriaDto())
				.toList();
	}

	@Override
	public void delete(Long id) {
		categoriaRepository.deleteById(id);

	}

	@Override
	public void deleteAll() {
		categoriaRepository.deleteAll();

	}

}
