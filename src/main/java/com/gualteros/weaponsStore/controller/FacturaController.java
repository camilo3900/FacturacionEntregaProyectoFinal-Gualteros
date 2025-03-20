package com.gualteros.weaponsStore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.gualteros.weaponsStore.models.Factura;
import com.gualteros.weaponsStore.models.dto.FacturaDto;
import com.gualteros.weaponsStore.models.request.FacturaRequest;
import com.gualteros.weaponsStore.service.FacturaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;


@RestController
@RequestMapping("/facturas")
public class FacturaController {
	

	@Autowired
	private FacturaService facturaService;
	//OBTENER TODAS
	@GetMapping
	@Operation(summary = "Obtener Facturas", description = "Obtener todas las Facturas de la DB.")
	@ApiResponse(responseCode="200", description = "Facturas obtenidas!")
	public ResponseEntity<List<FacturaDto>> getAll(){
		return new ResponseEntity<>(facturaService.getAll(), HttpStatus.OK);
	}
	//Agregar factura
	@PostMapping
    @Operation(summary = "Guardar Factura", description = "Se guarda una factura en la DB.")
    @ApiResponse(responseCode = "200", description  = "Factura guardada en la DB")
    public ResponseEntity<FacturaDto> crearFactura(@Parameter(description = "Objeto Factura", required = true) @RequestBody FacturaRequest facturaNueva){
            return new ResponseEntity<>(facturaService.insert(facturaNueva), HttpStatus.OK);
    }
	
    
	//obtener por codigo
	@GetMapping("/por-codigo")
	public ResponseEntity<FacturaDto> buscarPorCodigo(@RequestParam("code") String code) {
		return ResponseEntity.ok(facturaService.getByCode(code));
	}


    @DeleteMapping("/eliminar/{id}")
    @Operation(summary = "Eliminar Factura", description = "Se elimina factura por codigo.")
    @ApiResponse(responseCode = "200", description  = "Factura eliminada")
    public ResponseEntity<String> eliminar(@PathVariable("id") String idFactura){
        facturaService.deleteFactura(idFactura);
        return new ResponseEntity<>("Factura eliminada!", HttpStatus.OK);
    }

    //traer facturas de un cliente
    @GetMapping("/por-cliente")
    @Operation(summary = "Obtener Facturas por Cliente", description = "Obtiene todas las facturas de un cliente.")
    @ApiResponse(responseCode = "200", description  = "Facturas de un cliente obtenidas")
    public ResponseEntity<List<FacturaDto>> getFacturasFromCliente(@Parameter(description = "identificador unico de cliente.") @RequestParam("cliente") Long clienteId){
        return new ResponseEntity<>(facturaService.getFacturasByCliente(clienteId), HttpStatus.OK);
    }

    @GetMapping("por-precio")
    @Operation(summary = "Obtener Facturas por Pago", description = "Obtiene todas las facturas ordenadas por pago de mayor a menor.")
    @ApiResponse(responseCode = "200", description  = "Facturas  obtenidas")
    public ResponseEntity<List<FacturaDto>> getByPago(){
        return new ResponseEntity<>(facturaService.obtenerPorPrecio(), HttpStatus.OK);
    }
	//obtener por fechaEmision
	@GetMapping("/por-fecha")
	public ResponseEntity<List<FacturaDto>> ordenarPorFecha(){
		return new ResponseEntity<>(facturaService.getFacturasPorFecha(), HttpStatus.OK);
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





	
	
}
