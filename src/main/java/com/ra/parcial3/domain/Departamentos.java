package com.ra.parcial3.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class Departamentos {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nombreDepartamento;

    @OneToMany(mappedBy = "idDepartamento")
    private Set<Municipios> idMunicipios;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "empleados_id")
    private Empleados empleados;

}
