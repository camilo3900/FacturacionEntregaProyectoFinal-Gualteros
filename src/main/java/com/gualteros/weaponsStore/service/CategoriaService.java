package com.gualteros.weaponsStore.service;


import java.util.List;
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
		
		for(Categoria c : categoriaList){
            categoriaRepository.findAll().forEach(it->{
                if(it.getNombre().contains(c.getNombre())){
                    throw new RuntimeException("Categorias Repetidas!");
                }
            });    
        }
        categoriaRepository.saveAll(categoriaList);

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
					if(categoriaEncontrada != null){
						throw new RuntimeException("Categoria ya existe!");
					}
			return categoriaRepository.save(categoria).toCategoriaDto();
	
	}

	@Override
	public List<CategoriaDto> getAll() {
		return categoriaRepository.findAll().stream()
				.map(it -> it.toCategoriaDto())
				.toList();
	}

	public List<CategoriaDto> getAllOrder(){
		return categoriaRepository.getAllCategoriasOrder()
		.stream().map(it->it.toCategoriaDto())
		.toList();
	}

	@Override
	public CategoriaDto getById(Long idCategoria) {
		return categoriaRepository.findById(idCategoria)
				.map(it -> it.toCategoriaDto())
				.orElseThrow(()-> new RuntimeException("Categoria no encontrada"));
	}

	@Override
	public CategoriaDto update(CategoriaDto categoria, Long id) {
		//se valida la existencia de la categoria
		Categoria c = categoriaRepository.findById(id)
				.orElseThrow(()-> new RuntimeException("Categoria no encontrada"));
		if (categoria.getNombreDto() != null) {
			c.setNombre(categoria.getNombreDto());
		}//si no es nulo, se agrega
		if (categoria.getDescDto() != null) {
			c.setDesc(categoria.getDescDto());
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
		Categoria categoriaEncontrada = categoriaRepository.findById(id)
		.orElseThrow(()-> new RuntimeException("Categoria no encontrada!"));
		categoriaEncontrada.eliminarProductos();
		categoriaRepository.deleteById(id);
	}
	
	@Override
	public void deleteAll() {
		categoriaRepository.deleteAll();
	}

}
