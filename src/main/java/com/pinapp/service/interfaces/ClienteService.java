package com.pinapp.service.interfaces;

import com.pinapp.model.dto.ClienteAvarageDTO;
import com.pinapp.model.dto.ClienteDTO;

import java.util.List;

public interface ClienteService {

    ClienteDTO saveClient(ClienteDTO clienteDTO);

    List<ClienteDTO> listClient();

    ClienteAvarageDTO promedioCliente();
}
