package com.pinapp.service.impl;

import com.pinapp.model.dto.ClienteAvarageDTO;
import com.pinapp.model.dto.ClienteDTO;
import com.pinapp.model.entity.Cliente;
import com.pinapp.repository.ClienteRepository;
import com.pinapp.service.interfaces.ClienteService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    ClienteRepository clienteRepository;


    @Override
    public ClienteDTO saveClient(ClienteDTO clienteDTO) {
        Cliente cliente = dtoToEntity(clienteDTO);
        clienteRepository.save(cliente);
        clienteDTO.setId(cliente.getId());
        return clienteDTO;
    }

    private Cliente dtoToEntity(ClienteDTO clienteDTO) {
        return Cliente
                .builder()
                .nombre(clienteDTO.getNombre())
                .apellido(clienteDTO.getApellido())
                .edad(clienteDTO.getEdad())
                .fechaNacimiento(clienteDTO.getFechaNacimiento())
                .build();
    }

    private ClienteDTO entityToDto(Cliente cliente) {
        return ClienteDTO
                .builder()
                .nombre(cliente.getNombre())
                .id(cliente.getId())
                .apellido(cliente.getApellido())
                .edad(cliente.getEdad())
                .fechaNacimiento(cliente.getFechaNacimiento())
                .build();
    }

    @Override
    public List<ClienteDTO> listClient() {
        List<Cliente> clients = clienteRepository.findAll();
        return clients.stream().map(cliente -> entityToDto(cliente)).collect(Collectors.toList());
    }

    @Override
    public ClienteAvarageDTO promedioCliente() {
        List<Cliente> clients = clienteRepository.findAll();
        ClienteAvarageDTO clienteAvarageDTO = new ClienteAvarageDTO();
        if (CollectionUtils.isNotEmpty(clients)) {
            clienteAvarageDTO.setAgeAvarage(clients.stream().mapToInt(Cliente::getEdad).sum() / CollectionUtils.size(clients));
            double standardAge = 0;
            for (Cliente client : clients) {
                standardAge += Math.pow(client.getEdad() - clienteAvarageDTO.getAgeAvarage(), 2);
            }
            standardAge = standardAge / CollectionUtils.size(clients);
            standardAge = Math.sqrt(standardAge);
            clienteAvarageDTO.setStandardAge(standardAge);
        }
        return clienteAvarageDTO;
    }
}
