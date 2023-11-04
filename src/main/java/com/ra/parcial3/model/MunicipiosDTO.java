package com.ra.parcial3.model;

import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class MunicipiosDTO {

    private Long id;

    @Size(max = 255)
    private String nombreMunicipio;

    private Long idDepartamento;

}
