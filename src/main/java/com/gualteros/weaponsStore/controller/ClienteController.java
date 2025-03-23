package com.gualteros.weaponsStore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.gualteros.weaponsStore.models.Cliente;
import com.gualteros.weaponsStore.models.dto.ClienteDto;
import com.gualteros.weaponsStore.service.ClienteService;
import com.gualteros.weaponsStore.utils.BaseApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.*;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/clientes")
@Tag(name = "Clientes Controller", description =  "Crud de Clientes")
public class ClienteController {
	@Autowired
	private ClienteService clienteService;
	//GUARDA UN CLIENTE
	@PostMapping("/")
	@Operation(summary = "Guarda Cliente", description = "Se guarda un cliente nuevo en la DB.")
	@ApiResponses({
		@ApiResponse(responseCode = "201", description = "Cliente creado con exito"),
        @ApiResponse(responseCode = "500", description = "No se pudo guardar el cliente debido a errores en la validación")
	})
	public ResponseEntity<?> crearCliente(@RequestBody Cliente clienteNuevo) {
		try {
			clienteService.insert(clienteNuevo);
        return ResponseEntity.ok(BaseApiResponse.success(String.format("Cliente %s guardado", clienteNuevo.getApellido()), clienteNuevo));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(BaseApiResponse.error(HttpStatus.valueOf(500), e.getMessage()));
		}
    }
	//AGREGA TODOS
	@PostMapping("/agregar-todos")
	@Operation(summary = "Guarda todos", description = "Se guarda todo el listado de clientes en la DB.")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "Todo agregado"),
		@ApiResponse(responseCode = "500", description = "Problemas de validación del servidor")
	})
	public ResponseEntity<BaseApiResponse> agregarTodos(@RequestBody List<Cliente> clientes) {
		try {
			clienteService.insertAll(clientes);
		return ResponseEntity.ok().body(BaseApiResponse.success("Clientes Creados", null));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(BaseApiResponse.error(HttpStatus.valueOf(500), e.getMessage()));
		}
	}
	//LISTA TODOS LOS CLIENTES
	@GetMapping("/todos")
	@Operation(summary = "Lista todos los Clientes", description = "Se obtienen todos los clientes de la DB.")
	@ApiResponse(responseCode = "200", description = "Operación exitosa!")
	public ResponseEntity<BaseApiResponse> obtenerTodos(){
		return new ResponseEntity<>(BaseApiResponse.success("Lista de clientes filtrada!", clienteService.getAll()), HttpStatus.OK);
	}
	//LISTA TODOS EN ORDEN ALFABETICO
	@GetMapping("todos-ordenados")
	@Operation(summary = "Lista todos ordenados", description = "Se obtienen todos los clientes ordenados por nombre")
	@ApiResponse(responseCode = "200", description = "Recolección de los datos exitosa")
	public ResponseEntity<BaseApiResponse> obtenerTodosOrdenados(){
        return new ResponseEntity<>(BaseApiResponse.success("Datos obtenidos y ordenados!", clienteService.obtenerClientesOrdenados()), HttpStatus.OK);
    }
	//OBTIENE UN CLIENTE POR ID
	@GetMapping("/encontrar/{id}")
	@Operation(summary = "Obtiene Cliente", description = "Se busca un cliente por id en la DB.")
	@ApiResponses({
		@ApiResponse(responseCode = "302", description = "Cliente localizado."),
		@ApiResponse(responseCode = "404", description = "Cliente no encontrado")
	})
	public ResponseEntity<?> obtenerClienteId(@PathVariable("id") Long idCliente){
		try {
			return new ResponseEntity<>(BaseApiResponse.success("Cliente encontrado!",clienteService.getById(idCliente)), HttpStatus.FOUND);
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}
	//FILTRA LOS CLIENTES POR NOMBRE
	@GetMapping("/por-nombre")
	@Operation(summary = "Obtiene Clientes por nombre", description = "Se filtran los clientes por nombre.")
	@ApiResponse(responseCode = "302", description = "Busqueda exitosa")
	public ResponseEntity<BaseApiResponse> obtenerPorNombre(@RequestParam String nombreBuscar){
		return new ResponseEntity<>(BaseApiResponse.success("Se filtraron los clientes!",clienteService.getByName(nombreBuscar)), HttpStatus.FOUND);
	}

	@PutMapping("/actualizar/{id}")
	@Operation(summary = "Actualiza Cliente", description = "Se actualiza un cliente de la base de datos.")
	@ApiResponse(responseCode = "200", description = "Se actualiza el juego")
	public ResponseEntity<BaseApiResponse> actualizaCliente(@PathVariable("id") Long idCliente, @RequestBody ClienteDto clienteActualizar){
		clienteService.update(clienteActualizar, idCliente);
        return ResponseEntity.ok(BaseApiResponse.success("Cliente actualizado", null));
	}
	@DeleteMapping("/eliminar/{id}")
	@Operation(summary = "Elimina Cliente", description = "Se elimina un cliente por id de la DB.")
	@ApiResponse(responseCode = "200", description = "Cliente eliminado con exito!")
	public ResponseEntity<BaseApiResponse> eliminarCliente(@PathVariable("id") Long idCliente){
		clienteService.delete(idCliente);
		return new ResponseEntity<>(BaseApiResponse.error(HttpStatus.valueOf(500), "CLIENTE ELIMINADO!"), HttpStatus.OK);
	}
	@DeleteMapping("/eliminar-todo")
	@Operation(summary = "Elimina todos", description = "Se eliminan todos los registros de la tabla clientes.")
	@ApiResponse(responseCode = "200", description = "Se eliminan todos los clientes")
	public ResponseEntity<BaseApiResponse> eliminarTodos(){
		clienteService.deleteAll();
		return new ResponseEntity<>(BaseApiResponse.success("Clientes eliminados!", null), HttpStatus.OK);
	}
}
