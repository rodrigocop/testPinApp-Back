package com.pinapp.model.dto;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClienteAvarageDTO {

    private Integer ageAvarage;
    private Double standardAge;


}
