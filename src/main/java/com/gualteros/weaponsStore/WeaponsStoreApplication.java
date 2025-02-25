package com.gualteros.weaponsStore;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.gualteros.weaponsStore.models.Comprador;
import com.gualteros.weaponsStore.models.Producto;
import com.gualteros.weaponsStore.models.dto.CompradorDto;
import com.gualteros.weaponsStore.models.extra.Categoria;
import com.gualteros.weaponsStore.models.extra.Datos;
import com.gualteros.weaponsStore.service.CompradorService;
import com.gualteros.weaponsStore.service.ProductoService;

import io.micrometer.common.lang.Nullable;
import lombok.AllArgsConstructor;

@SpringBootApplication
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class WeaponsStoreApplication implements CommandLineRunner {
	@Nullable
	private CompradorService compradorService = null;
	@Nullable
	private ProductoService productoService = null;

	public static void main(String[] args) {
		SpringApplication.run(WeaponsStoreApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		/* insertarTodosCompradores(compradorService); */
		
	}
	//RECOGEMOS TODOS LOS COMPRADORES
	public static void obtenerCompradores(CompradorService service) {
		service.getAll().forEach(it -> System.out.println(it.toString()));
	}
	//FILTRAMOS COMPRADOR POR ID
	public static CompradorDto getComprador(CompradorService service) {
		CompradorDto compradorEncontrado = service.getObjectById(10L);
		if (compradorEncontrado == null) {
			System.out.println("COMPRADOR NO EXISTE");
			return null; // retorna null si no encuentra el comprador
		} else {
			return compradorEncontrado;
		}

	}
	// PREPOBLAMOS LA TABLA DE PRODUCTOS
	/* public static void agregarTodosProductos(ProductoService service) {
		//CREAMOS LOS PRODUCTOS
		Producto p1 = new Producto(null, "Balas para carabina", Categoria.MUNICIONES, 30.0, 120, null);
		Producto p2 = new Producto(null, "Daga de supervivencia", Categoria.DAGAS, 150.0, 10, null);
		Producto p3 = new Producto(null, "Rifle de asalto", Categoria.RIFLES, 1000.0, 2, null);
		Producto p4 = new Producto(null, "Pistola compacta", Categoria.PISTOLAS, 350.0, 6, null);
		Producto p5 = new Producto(null, "Munición para rifle", Categoria.MUNICIONES, 50.0, 100, null);
		Producto p6 = new Producto(null, "Rifle semiautomático", Categoria.RIFLES, 700.0, 3, null);
		Producto p7 = new Producto(null, "Pistola de defensa", Categoria.PISTOLAS, 250.0, 10, null);
		Producto p8 = new Producto(null, "Guantes de caza", Categoria.EQUIPOS, 20.0, 50, null);
		Producto p9 = new Producto(null, "Sensor de Movimiento Infrarrojo", Categoria.SISTEMAS_DE_SEGURIDAD, 45.00, 100, null);
		Producto p10 = new Producto(null, "Dispositivo GPS de Seguimiento", Categoria.SISTEMAS_DE_SEGURIDAD, 60.00, 25, null);
		// INSERTAMOS LOS PRODUCTOS EN LA BASE DE DATOS
		service.insertAll(List.of(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10));
	} */
	//AGREGAMOS UN COMPRADOR A LA TABLA
	public static void agregarComprador(CompradorService service) {
		// CREAMOS UN COMPRADOR
		Comprador comprador = new Comprador(null, 12345678L,"Carlos", "González",new Datos("Calle 123", "Bogotá", "3001234567"), null);
		// INSERTAMOS EL COMPRADOR EN LA BASE DE DATOS
		service.insertObject(comprador);
	}

	// FUNCION PARA POBLAR LA TABLA COMPRADORES
	public static void insertarTodosCompradores(CompradorService service) {
		Comprador c1 = new Comprador(null, 12345678L, "Juan", "Pérez",
				new Datos("Calle 123", "Buenos Aires", "123456789"), null);
		Comprador c2 = new Comprador(null, 87654321L, "María", "Gómez",
				new Datos("Avenida 456", "Córdoba", "987654321"), null);
		Comprador c3 = new Comprador(null, 56781234L, "Carlos", "Rodríguez",
				new Datos("Diagonal 789", "Rosario", "456123789"), null);
		Comprador c4 = new Comprador(null, 34567812L, "Laura", "Fernández",
				new Datos("Ruta 32", "Mendoza", "321987654"), null);
		Comprador c5 = new Comprador(null, 65432187L, "Luis", "Martínez", new Datos("Pasaje 15", "Salta", "159753468"),
				null);
		Comprador c6 = new Comprador(6L, 78912345L, "Ana", "Sánchez",
				new Datos("Callejón 77", "San Juan", "753951852"), null);
		Comprador c7 = new Comprador(null, 91234567L, "Pedro", "López",
				new Datos("Camino Real", "Tucumán", "852369741"), null);
		Comprador c8 = new Comprador(null, 14725836L, "Sofía", "Díaz",
				new Datos("Boulevard Norte", "Neuquén", "369258147"), null);

        // INSERTAMOS LOS COMPRADORES EN LA BASE DE DATOS
		service.insertAll(List.of(c1, c2, c3, c4, c5, c6, c7, c8));
	}

}
