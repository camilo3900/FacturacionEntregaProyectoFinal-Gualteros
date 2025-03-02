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
		clienteService.insert(clienteNuevo);
        return ResponseEntity.ok("Cliente creado");
    }
	@PostMapping("/all")
	public ResponseEntity<String> agregarTodos(@RequestBody List<Cliente> clientes) {
		clienteService.insertAll(clientes);
		return ResponseEntity.ok("Clientes creados");
	}
	@GetMapping("/")
	public ResponseEntity<List<ClienteDto>> obtenerTodos(){
		return new ResponseEntity<>(clienteService.getAll(), HttpStatus.OK);
	}
	@GetMapping("/por-nombre")
	public ResponseEntity<List<ClienteDto>> obtenerPorNombre(@RequestParam String nombreBuscar){
		return new ResponseEntity<>(clienteService.getByName(nombreBuscar), HttpStatus.FOUND);
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<String> eliminarCliente(@PathVariable("id") Long idCliente){
		clienteService.delete(idCliente);
		return new ResponseEntity<>("CLIENTE ELIMINADO!", HttpStatus.OK);
	}
}
