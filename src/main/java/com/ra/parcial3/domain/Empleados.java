package com.ra.parcial3.domain;

import com.ra.parcial3.model.Genero;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class Empleados {

    @Id
    @Column(nullable = false, updatable = false)
    @SequenceGenerator(
            name = "primary_sequence",
            sequenceName = "primary_sequence",
            allocationSize = 1,
            initialValue = 10000
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "primary_sequence"
    )
    private Long id;

    @Column
    private String apellidos;

    @Column
    private String nombres;

    @Column
    @Enumerated(EnumType.STRING)
    private Genero genero;

    @Column
    private String direccion;

    @Column
    private String telefono;

    @Column
    private Double salario;

    @Column
    private Boolean activo;

    @Column
    private String codigo;

}
