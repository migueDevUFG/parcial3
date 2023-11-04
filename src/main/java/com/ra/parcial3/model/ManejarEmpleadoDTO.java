package com.ra.parcial3.model;

import javax.validation.constraints.Size;

public class ManejarEmpleadoDTO {


    @Size(max = 255)
    private String mensaje;

    private Boolean activo;
}
