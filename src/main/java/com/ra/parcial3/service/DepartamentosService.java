package com.ra.parcial3.service;

import com.ra.parcial3.domain.Departamentos;
import com.ra.parcial3.domain.Empleados;
import com.ra.parcial3.model.DepartamentosDTO;
import com.ra.parcial3.repos.DepartamentosRepository;
import com.ra.parcial3.repos.EmpleadosRepository;
import com.ra.parcial3.util.NotFoundException;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class DepartamentosService {

    private final DepartamentosRepository departamentosRepository;
    private final EmpleadosRepository empleadosRepository;

    public DepartamentosService(final DepartamentosRepository departamentosRepository,
            final EmpleadosRepository empleadosRepository) {
        this.departamentosRepository = departamentosRepository;
        this.empleadosRepository = empleadosRepository;
    }

    public List<DepartamentosDTO> findAll() {
        final List<Departamentos> departamentoses = departamentosRepository.findAll(Sort.by("id"));
        return departamentoses.stream()
                .map(departamentos -> mapToDTO(departamentos, new DepartamentosDTO()))
                .toList();
    }

    public DepartamentosDTO get(final Long id) {
        return departamentosRepository.findById(id)
                .map(departamentos -> mapToDTO(departamentos, new DepartamentosDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Long create(final DepartamentosDTO departamentosDTO) {
        final Departamentos departamentos = new Departamentos();
        mapToEntity(departamentosDTO, departamentos);
        return departamentosRepository.save(departamentos).getId();
    }

    public void update(final Long id, final DepartamentosDTO departamentosDTO) {
        final Departamentos departamentos = departamentosRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(departamentosDTO, departamentos);
        departamentosRepository.save(departamentos);
    }

    public void delete(final Long id) {
        departamentosRepository.deleteById(id);
    }

    private DepartamentosDTO mapToDTO(final Departamentos departamentos,
            final DepartamentosDTO departamentosDTO) {
        departamentosDTO.setId(departamentos.getId());
        departamentosDTO.setNombreDepartamento(departamentos.getNombreDepartamento());
        departamentosDTO.setEmpleados(departamentos.getEmpleados() == null ? null : departamentos.getEmpleados().getId());
        return departamentosDTO;
    }

    private Departamentos mapToEntity(final DepartamentosDTO departamentosDTO,
            final Departamentos departamentos) {
        departamentos.setNombreDepartamento(departamentosDTO.getNombreDepartamento());
        final Empleados empleados = departamentosDTO.getEmpleados() == null ? null : empleadosRepository.findById(departamentosDTO.getEmpleados())
                .orElseThrow(() -> new NotFoundException("empleados not found"));
        departamentos.setEmpleados(empleados);
        return departamentos;
    }

    public boolean nombreDepartamentoExists(final String nombreDepartamento) {
        return departamentosRepository.existsByNombreDepartamentoIgnoreCase(nombreDepartamento);
    }

}
