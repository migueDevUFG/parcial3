package com.ra.parcial3.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Size;

@Getter
@Setter
public class EmpleadoModificadoDTO {
    private Boolean activo;

    @Size(max = 255)
    private String mensaje;
}

