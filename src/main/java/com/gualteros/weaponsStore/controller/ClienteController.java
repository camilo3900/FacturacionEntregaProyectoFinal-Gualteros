package com.gualteros.weaponsStore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.gualteros.weaponsStore.models.Cliente;
import com.gualteros.weaponsStore.models.dto.ClienteDto;
import com.gualteros.weaponsStore.service.ClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
	@Autowired
	private ClienteService clienteService;
	@PostMapping("")
	public ResponseEntity<String> crearCliente(@RequestBody Cliente clienteNuevo) {
		try {
			clienteService.insert(clienteNuevo);
        return ResponseEntity.ok("Cliente creado");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
    }
	@PostMapping("/todos")
	public ResponseEntity<String> agregarTodos(@RequestBody List<Cliente> clientes) {
		try {
			clienteService.insertAll(clientes);
		return ResponseEntity.ok("Clientes Creados");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}
	@GetMapping("/todos")
	public ResponseEntity<List<ClienteDto>> obtenerTodos(){
		return new ResponseEntity<>(clienteService.getAll(), HttpStatus.OK);
	}
	@GetMapping("/encontrar/{id}")
	public ResponseEntity<?> obtenerClienteId(@PathVariable("id") Long idCliente){
		try {
			return new ResponseEntity<>(clienteService.getById(idCliente), HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.internalServerError().body(e.getMessage());
		}
	}
	@GetMapping("/encontrar/por-nombre")
	public ResponseEntity<List<ClienteDto>> obtenerPorNombre(@RequestParam String nombreBuscar){
		return new ResponseEntity<>(clienteService.getByName(nombreBuscar), HttpStatus.FOUND);
	}

	@PutMapping("/actualizar/{id}")
	public ResponseEntity<String> actualizaCliente(@PathVariable("id") Long idCliente, @RequestBody ClienteDto clienteActualizar){
		clienteService.update(clienteActualizar, idCliente);
        return ResponseEntity.ok("Cliente actualizado");
	}
	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<String> eliminarCliente(@PathVariable("id") Long idCliente){
		clienteService.delete(idCliente);
		return new ResponseEntity<>("CLIENTE ELIMINADO!", HttpStatus.OK);
	}
	@DeleteMapping("/eliminar-todo")
	public ResponseEntity<String> eliminarTodos(){
		clienteService.deleteAll();
		return new ResponseEntity<>("Clientes eliminados!", HttpStatus.OK);
	}
}
