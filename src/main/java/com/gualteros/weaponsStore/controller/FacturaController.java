package com.gualteros.weaponsStore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.gualteros.weaponsStore.models.request.FacturaRequest;
import com.gualteros.weaponsStore.service.FacturaService;
import com.gualteros.weaponsStore.utils.BaseApiResponse;
import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@RequestMapping("/facturas")
@Tag(name = "Facturas Controller", description = "CRUD de Facturas")
public class FacturaController {
	

	@Autowired
	private FacturaService facturaService;
	//LISTAR TODAS
	@GetMapping
	@Operation(summary = "Listar Facturas", description = "Se buscan todas las Facturas de la DB.")
	@ApiResponse(responseCode="200", description = "Facturas obtenidas!")
	public ResponseEntity<BaseApiResponse> getAll(){
		return new ResponseEntity<>(BaseApiResponse.success( "Facturas encontradas!" ,facturaService.getAll()), HttpStatus.OK);
	}
	//GUARDAR FACTURA
	@PostMapping("/")
    @Operation(summary = "Guardar Factura", description = "Se guarda una factura en la DB.")
    @ApiResponse(responseCode = "200", description  = "Factura guardada en la DB")
    public ResponseEntity<?> crearFactura(@Parameter(description = "Objeto Factura", required = true) @RequestBody FacturaRequest facturaNueva){
            try {
				return new ResponseEntity<>(BaseApiResponse.success("Factura Guardada", facturaService.insert(facturaNueva)), HttpStatus.OK);
			} catch (Exception e) {
				return ResponseEntity.internalServerError().body(BaseApiResponse.error(HttpStatus.valueOf(500), e.getMessage()));
			}
    }
	//GUARDAR TODOS
	@PostMapping("/agregar-todos")
	@Operation(summary = "Guarda todos", description = "Se guardan todas las facturas en la DB.")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "Facturas guardadas."),
		@ApiResponse(responseCode = "500", description = "No se pudieron guardar las facturas debido a errores en la validación")
	})
	public ResponseEntity<?> insertarTodo(@RequestBody List<FacturaRequest> facturasList){
		try {
			facturaService.insertAll(facturasList);
			return new ResponseEntity<>(BaseApiResponse.success("Facturas agregadas con exito!", null) , HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(BaseApiResponse.error(HttpStatus.valueOf(500), e.getMessage()));
		}
		
	}
	//OBTIENE FACTURA POR CODIGO
	@GetMapping("/por-codigo")
	@Operation(summary = "Obtiene factura", description = "Se busca una factura por id en la DB.")
	@ApiResponse(responseCode = "200", description = "Exito al obtener producto por id")
	public ResponseEntity<BaseApiResponse> buscarPorCodigo(@RequestParam("code") String code) {
		return ResponseEntity.ok().body(BaseApiResponse.success("Factura conseguida.", facturaService.getByCode(code)));
	}

	//ELIMINA FACTURA POR ID
    @DeleteMapping("/eliminar/{id}")
    @Operation(summary = "Eliminar Factura", description = "Se elimina factura por codigo.")
    @ApiResponse(responseCode = "200", description  = "Factura eliminada")
    public ResponseEntity<BaseApiResponse> eliminar(@PathVariable("id") String idFactura){
        facturaService.deleteFactura(idFactura);
        return new ResponseEntity<>(BaseApiResponse.success("Factura eliminada!", null), HttpStatus.OK);
    }

    //LISTAR FACTURAS DE UN CLIENTE
    @GetMapping("/por-cliente")
    @Operation(summary = "Lista Facturas por Cliente", description = "Obtiene todas las facturas de un cliente.")
    @ApiResponse(responseCode = "200", description  = "Facturas de un cliente obtenidas")
    public ResponseEntity<BaseApiResponse> getFacturasFromCliente(@Parameter(description = "identificador unico de cliente.") @RequestParam("cliente") Long clienteId){
        return new ResponseEntity<>(BaseApiResponse.success("Cliente por Factura", facturaService.getFacturasByCliente(clienteId)), HttpStatus.OK);
    }
	//LISTAR FACTURAS POR PRECIOS
    @GetMapping("/por-precio")
    @Operation(summary = "Lista Facturas por Pago", description = "Obtiene todas las facturas ordenadas por pago de mayor a menor.")
    @ApiResponse(responseCode = "200", description  = "Facturas  obtenidas")
    public ResponseEntity<BaseApiResponse> getByPago(){
        return new ResponseEntity<>(BaseApiResponse.success("Obtenido por pago", facturaService.obtenerPorPrecio()), HttpStatus.OK);
    }
	//LISTAR FACTURAS POR FECHA
	@GetMapping("/por-fecha")
	@Operation(summary = "Lista Facturas por Fecha", description = "Se Obtiene todas las facturas de la DB por fecha ascendente.")
	@ApiResponse(responseCode = "200", description = "Se obtienen facturas por fecha")
	public ResponseEntity<BaseApiResponse> ordenarPorFecha(){
		return new ResponseEntity<>(BaseApiResponse.success("Obtenido por fecha", facturaService.getFacturasPorFecha()), HttpStatus.OK);
	}
	//ELIMINAR TODAS LAS FACTURAS
	@DeleteMapping("/eliminar-todo")
	@Operation(summary = "Elimina todos", description = "Se eliminan todos los registros de la tabla Facturas.")
	@ApiResponse(responseCode = "200", description  = "Todos los registros de la tabla Facturas han sido eliminados.")
	public ResponseEntity<BaseApiResponse> eliminarTodo(){
		facturaService.deleteAll();
		return new ResponseEntity<>(BaseApiResponse.success("Facturas eliminadas!", null), HttpStatus.OK);
	}
}
