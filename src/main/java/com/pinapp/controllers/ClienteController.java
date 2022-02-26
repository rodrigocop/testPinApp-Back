package com.pinapp.controllers;

import com.pinapp.model.dto.ClienteAvarageDTO;
import com.pinapp.model.dto.ClienteDTO;
import com.pinapp.service.interfaces.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping(value = "api/cliente")
public class ClienteController {

    @Autowired
    ClienteService figuraService;

    @GetMapping(value = "/listclientes", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(description = "Devuelve el listado de clientes existentes")
    public List<ClienteDTO> listClient() {
        return figuraService.listClient();
    }

    @GetMapping(value = "/kpideclientes", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(description = "Devuelve el promedio de edades entre los clientes")
    public ClienteAvarageDTO promedioCliente() {
        return figuraService.promedioCliente();
    }

    @PostMapping(value = "/creacliente", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(description = "Crea un cliente")
    public ClienteDTO setFigura(@RequestBody @Valid ClienteDTO clienteDTO) throws Exception {
        return figuraService.saveClient(clienteDTO);
    }
}
