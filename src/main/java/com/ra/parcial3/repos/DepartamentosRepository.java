package com.ra.parcial3.repos;

import com.ra.parcial3.domain.Departamentos;
import org.springframework.data.jpa.repository.JpaRepository;


public interface DepartamentosRepository extends IGenericRepo<Departamentos, Long> {

    boolean existsByNombreDepartamentoIgnoreCase(String nombreDepartamento);

    public Departamentos findByNombreDepartamento(String nombreDepartamento);

}
