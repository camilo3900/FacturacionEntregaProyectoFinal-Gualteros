package com.gualteros.weaponsStore;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.gualteros.weaponsStore.models.Comprador;
import com.gualteros.weaponsStore.models.Datos;
import com.gualteros.weaponsStore.models.dto.CompradorDto;
import com.gualteros.weaponsStore.service.CompradorService;

import io.micrometer.common.lang.Nullable;
import lombok.AllArgsConstructor;

@SpringBootApplication
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class WeaponsStoreApplication implements CommandLineRunner {
	@Nullable
	private CompradorService compradorService = null;

	public static void main(String[] args) {
		SpringApplication.run(WeaponsStoreApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("\nCOMPRADOR ENCONTRADO: "+getComprador(compradorService).toString());
	}

	private void obtenerCompradores(CompradorService service) {
		service.getAll().forEach(it -> System.out.println(it.toString()));
	}

	private CompradorDto getComprador(CompradorService service) {
		CompradorDto compradorEncontrado = service.getObjectById(10L);
		if (compradorEncontrado == null) {
			System.out.println("COMPRADOR NO EXISTE");
			return null; // retorna null si no encuentra el comprador
		} else {
			return compradorEncontrado;
		}

	}

	private void agregarComprador(CompradorService service) {
		Comprador comprador = new Comprador(
				null, 
				12345678L,
				"Carlos",
				"González", 
				new Datos("Calle 123", "Bogotá", "3001234567") 
		);
		service.insertObject(comprador);
	}

	// funcion para prepoblar la tabla de compradores
	private void insertarTodosCompradores(CompradorService service) {
		Comprador c1 = new Comprador(null, 12345678L, "Juan", "Pérez",
				new Datos("Calle 123", "Buenos Aires", "123456789"));
		Comprador c2 = new Comprador(null, 87654321L, "María", "Gómez",
				new Datos("Avenida 456", "Córdoba", "987654321"));
		Comprador c3 = new Comprador(null, 56781234L, "Carlos", "Rodríguez",
				new Datos("Diagonal 789", "Rosario", "456123789"));
		Comprador c4 = new Comprador(null, 34567812L, "Laura", "Fernández",
				new Datos("Ruta 32", "Mendoza", "321987654"));
		Comprador c5 = new Comprador(null, 65432187L, "Luis", "Martínez", new Datos("Pasaje 15", "Salta", "159753468"));
		Comprador c6 = new Comprador(6L, 78912345L, "Ana", "Sánchez",
				new Datos("Callejón 77", "San Juan", "753951852"));
		Comprador c7 = new Comprador(null, 91234567L, "Pedro", "López",
				new Datos("Camino Real", "Tucumán", "852369741"));
		Comprador c8 = new Comprador(null, 14725836L, "Sofía", "Díaz",
				new Datos("Boulevard Norte", "Neuquén", "369258147"));
		service.insertAll(List.of(c1, c2, c3, c4, c5, c6, c7, c8));
	}

}
