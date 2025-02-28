package com.gualteros.weaponsStore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
		facturaService.insert(facturaNueva);
        return ResponseEntity.ok("Factura agregada");
    }//agregar todos
	@PostMapping("/all")
	public ResponseEntity<String> insertarTodo(@RequestBody List<Factura> facturasList){
		facturaService.insertAll(facturasList);
		return new ResponseEntity<>("Facturas agregadas con exito!", org.springframework.http.HttpStatus.OK);
		
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
	
	
}
