package com.ra.parcial3.model;

import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class EmpleadosDTO {

    private Long id;

    @Size(max = 255)
    private String apellidos;

    @Size(max = 255)
    private String nombres;

    private Genero genero;

    @Size(max = 255)
    private String direccion;

    @Size(max = 255)
    private String telefono;

    private Double salario;

    private Boolean activo;

}
