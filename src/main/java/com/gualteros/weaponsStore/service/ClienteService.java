package com.gualteros.weaponsStore.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gualteros.weaponsStore.models.Cliente;
import com.gualteros.weaponsStore.models.dto.ClienteDto;
import com.gualteros.weaponsStore.repository.ClienteRepository;
import com.gualteros.weaponsStore.repository.FacturaRepository;

@Service
public class ClienteService implements BaseEntityOp<Cliente, ClienteDto> {
	
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired 
	private FacturaRepository facturaRepository;
	@Override
	public void insertAll(List<Cliente> clienteList) {
		// Validacion de duplicados por dni
		clienteList.forEach(it->{
			Cliente clienteEncontrado = clienteRepository.findByDni(it.getDni());
			if(clienteEncontrado != null){
				throw new RuntimeException(String.format("cliente con dni %s ya existe", it.getDni()));
			}
		});
		clienteRepository.saveAll(clienteList);
		
	}
	@Override
	public ClienteDto insert(Cliente cliente) {
		Cliente clienteEncontrado = clienteRepository.findByDni(cliente.getDni());
		if(clienteEncontrado != null){
			throw new RuntimeException(String.format("cliente con dni %s ya existe", cliente.getDni()));
		}
		return clienteRepository.save(cliente).toClienteDto();
	}

	@Override
	public List<ClienteDto> getAll() {
		return clienteRepository.findAll().stream()
				.map((it)->it.toClienteDto())
				.toList();
	}

	@Override
	public ClienteDto getById(Long id) {
		Cliente clienteEncontrado = clienteRepository.findById(id).orElse(null);
		if(clienteEncontrado==null){
			throw new RuntimeException("Cliente no encontrado");
		}
		return clienteEncontrado.toClienteDto();
	}

	@Override
	public ClienteDto update(ClienteDto clienteActualizar, Long id) {
		Cliente clienteEncontrado = clienteRepository.findById(id)
				.orElse(null);
		if(clienteEncontrado == null) {
			throw new RuntimeException("Cliente no encontrado");
		}
		//actualizacion atributos
		clienteEncontrado.actualizarCliente(clienteActualizar);
		return clienteRepository.save(clienteEncontrado).toClienteDto();
	}

	@Override
	public List<ClienteDto> getByName(String name) {
		return clienteRepository.findByName(name+"%")
				.stream().map((it) -> it.toClienteDto())
				.toList();
	}

	@Override
	public void delete(Long id) {
		Cliente clienteEncontrado = clienteRepository.findById(id).orElse(null);
		if(clienteEncontrado==null){
            throw new RuntimeException("Cliente no encontrado");
        }
		clienteEncontrado.getFacturas().forEach(f->facturaRepository.delete(f));
		clienteRepository.deleteById(id);
		
	}

	@Override
	public void deleteAll() {
		clienteRepository.deleteAll();
		
	}

}
