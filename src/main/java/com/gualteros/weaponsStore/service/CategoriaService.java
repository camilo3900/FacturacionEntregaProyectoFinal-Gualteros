package com.gualteros.weaponsStore.service;

import java.util.ArrayList;
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
		//category insert all
		List<Categoria> catList = new ArrayList<>();
		categoriaList.forEach(it->{
			if(validateCategory(it)!=null) {
				catList.add(it);
			}
		});
		categoriaRepository.saveAll(catList);

	}
	
	public Categoria validateCategory(Categoria cat) {
		Categoria categoriaEncontrada = categoriaRepository
				.findAll().stream()
				.filter(it -> it.getNombre().contains(cat.getNombre()))
				.findFirst().orElse(null);
				if(categoriaEncontrada!=null) {
					throw new RuntimeException("CATEGORIA REPETIDA!");
				}
				return cat;
	}

	@Override
	public CategoriaDto insert(Categoria categoria) {
			//validation category exist
			Categoria categoriaEncontrada = categoriaRepository.findAll().stream()
					.filter(it -> it.getNombre().contains(categoria.getNombre()))
					.findFirst().orElse(null);	
			if (categoriaEncontrada != null) {
				
				throw new RuntimeException("CATEGORIA YA EXISTE!");
			}
			return categoriaRepository.save(categoria).toCategoriaDto();
	
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
		//se valida la existencia de la categoria
		Categoria c = categoriaRepository.findById(id)
				.orElseThrow(NoSuchElementException::new);
		if (categoriaDto.getNombreDto() != null) {
			c.setNombre(categoriaDto.getNombreDto());
		}//si no es nulo, se agrega
		if (categoriaDto.getDescDto() != null) {
			c.setDesc(categoriaDto.getDescDto());
		}
		return categoriaRepository.save(c).toCategoriaDto();
	}

	@Override
	public List<CategoriaDto> getByName(String name) {
		//se lista por nombre (es case sensitive)
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
