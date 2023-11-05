package com.ra.parcial3.repos;

import com.ra.parcial3.domain.EmpleadosInactivos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpleadosInactivosRepository extends JpaRepository<EmpleadosInactivos, Long> {
}
