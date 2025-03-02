package com.gualteros.weaponsStore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.gualteros.weaponsStore.models.Factura;
import com.gualteros.weaponsStore.models.dto.FacturaDto;
import com.gualteros.weaponsStore.service.FacturaService;

@RestController
@RequestMapping("/facturas")
public class FacturaController {
	

	@Autowired
	private FacturaService facturaService;
	//agregar una factura
	@PostMapping("/")
	public ResponseEntity<String> agregarFactura(@RequestBody Factura facturaNueva){
		try {
			facturaService.insert(facturaNueva);
	        return ResponseEntity.ok("Factura agregada");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}

    }//agregar todos
	@PostMapping("/all")
	public ResponseEntity<String> insertarTodo(@RequestBody List<Factura> facturasList){
		try {
			facturaService.insertAll(facturasList);
			return new ResponseEntity<>("Facturas agregadas con exito!"
					, HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
		
	}
	//obtener todos
	@GetMapping("")
	public ResponseEntity<List<FacturaDto>> listarTodas() {
		return ResponseEntity.ok(facturaService.getAll());
	}
	//obtener por codigo
	@GetMapping("/por-codigo")
	public ResponseEntity<FacturaDto> buscarPorCodigo(@RequestParam("code") String code) {
		return ResponseEntity.ok(facturaService.getByCodigo(code));
	}
	//obtener por fechaEmision
	@GetMapping("/por-fecha")
	public ResponseEntity<List<FacturaDto>> ordenarPorFecha(){
		return new ResponseEntity<>(facturaService.ordenarPorFecha(), HttpStatus.OK);
	}
	//eliminar factura
	@DeleteMapping("/eliminar/{code}")
	public ResponseEntity<String> eliminarFactura(@PathVariable String code){
		facturaService.deleteFactura(code);
		return new ResponseEntity<>("Factura Eliminada!", HttpStatus.OK);
	}
	//eliminar todo
	@DeleteMapping("/all")
	public ResponseEntity<String> eliminarTodo(){
		facturaService.deleteAll();
		return new ResponseEntity<>("Se eliminaron todas las Facturas!", HttpStatus.OK);
	}
	
	
}
