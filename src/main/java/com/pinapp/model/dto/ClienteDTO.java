package com.pinapp.model.dto;

import java.time.LocalDate;
import lombok.*;

import javax.validation.constraints.NotNull;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClienteDTO {

    private Long id;

    @NotNull(message = "Nombre es obligatorio")
    private String nombre;

    @NotNull
    private String apellido;

    @NotNull
    private Integer edad;

    @NotNull
    private LocalDate fechaNacimiento;

}
