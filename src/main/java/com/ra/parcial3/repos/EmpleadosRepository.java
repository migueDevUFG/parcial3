package com.ra.parcial3.repos;

import com.ra.parcial3.domain.Empleados;
import org.springframework.data.jpa.repository.JpaRepository;


public interface EmpleadosRepository extends JpaRepository<Empleados, Long> {
}
