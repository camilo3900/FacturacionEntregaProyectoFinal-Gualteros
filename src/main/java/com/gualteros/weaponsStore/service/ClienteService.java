package com.gualteros.weaponsStore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gualteros.weaponsStore.models.Cliente;
import com.gualteros.weaponsStore.models.dto.ClienteDto;
import com.gualteros.weaponsStore.repository.ClienteRepository;

@Service
public class ClienteService implements BaseEntityOp<Cliente, ClienteDto> {
	
	@Autowired
	private ClienteRepository clienteRepository;

	@Override
	public void insertAll(List<Cliente> clienteList) {
		clienteRepository.saveAll(clienteList);
		
	}
	@Override
	public ClienteDto insert(Cliente cliente) {
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
		return clienteRepository.findById(id)
				.get().toClienteDto();
	}

	@Override
	public ClienteDto update(ClienteDto clienteDto, Long id) {
		Cliente clienteEncontrado = clienteRepository.findById(id)
				.orElse(null);
		if(clienteEncontrado != null) {
			throw new RuntimeException("Cliente no encontrado");
		}
		//actualizacion atributos
		clienteEncontrado = Cliente.builder()
		.nombre(clienteDto.getNombreDto())
		.apellido(clienteDto.getApellidoDto()).build();
		return clienteEncontrado.toClienteDto();
	}

	@Override
	public List<ClienteDto> getByName(String name) {
		return clienteRepository.findByName(name+"%")
				.stream().map((it) -> it.toClienteDto())
				.toList();
	}

	@Override
	public void delete(Long id) {
		clienteRepository.deleteById(id);
		
	}

	@Override
	public void deleteAll() {
		clienteRepository.deleteAll();
		
	}

}
