package com.ra.parcial3.model;

import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class DepartamentosDTO {

    private Long id;

    @Size(max = 255)
    private String nombreDepartamento;

    private Long empleados;

}
