package com.gualteros.weaponsStore.models;

import java.util.List;

import com.gualteros.weaponsStore.models.dto.CategoriaDto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "categorias")
@Data
@Builder
@AllArgsConstructor@NoArgsConstructor
public class Categoria {
	/*Relacion:
	 * 1-categoria-producto: ManyToMany*/
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Schema(description = "Identificador unico", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
	private Long id;
	@Column(name = "nombre_categoria")
	@Schema(description = "nombre de categoria", example = "Supervivencia")
	private String nombre;
	@Column(name = "descripcion")
	@Schema(description = "descripcion de categoria", example = "Herramientas y equipos esenciales para sobrevivir en entornos extremos y situaciones de emergencia.")
	private String desc;
	@ManyToMany(mappedBy = "categorias")
	@Schema(description = "Productos relacionados con la categoria", example = "[]")
	private List<Producto> productos;

	public void eliminarProductos() {
		List<Producto> productList = this.productos;
		productList.forEach(it->{	
			it.getCategorias().remove(this);
		});
		this.productos.clear();
	}
	public void agregarProducto(Producto producto){
		this.productos.add(producto);
		producto.getCategorias().add(this);
	}
	//type conversion to categoriaDto
	public CategoriaDto toCategoriaDto() {
		return CategoriaDto.builder()
				.nombreDto(this.nombre)
				.descDto(desc)
				.build();
	}
}
