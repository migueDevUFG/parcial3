package com.ra.parcial3.service;

import com.ra.parcial3.domain.Empleados;
import com.ra.parcial3.model.EmpleadosDTO;
import com.ra.parcial3.repos.EmpleadosRepository;
import com.ra.parcial3.util.NotFoundException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class EmpleadosService {

    private final EmpleadosRepository empleadosRepository;

    public EmpleadosService(final EmpleadosRepository empleadosRepository) {
        this.empleadosRepository = empleadosRepository;
    }

    public List<EmpleadosDTO> findAll() {
        final List<Empleados> empleadoses = empleadosRepository.findAll(Sort.by("id"));
        return empleadoses.stream()
                .map(empleados -> mapToDTO(empleados, new EmpleadosDTO()))
                .collect(Collectors.toList());
    }

    public EmpleadosDTO get(final Long id) {
        return empleadosRepository.findById(id)
                .map(empleados -> mapToDTO(empleados, new EmpleadosDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Long create(final EmpleadosDTO empleadosDTO) {
        final Empleados empleados = new Empleados();
        mapToEntity(empleadosDTO, empleados);
        return empleadosRepository.save(empleados).getId();
    }

    public void update(final Long id, final EmpleadosDTO empleadosDTO) {
        final Empleados empleados = empleadosRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(empleadosDTO, empleados);
        empleadosRepository.save(empleados);
    }

    public void delete(final Long id) {
        empleadosRepository.deleteById(id);
    }

    private EmpleadosDTO mapToDTO(final Empleados empleados, final EmpleadosDTO empleadosDTO) {
        empleadosDTO.setId(empleados.getId());
        empleadosDTO.setApellidos(empleados.getApellidos());
        empleadosDTO.setNombres(empleados.getNombres());
        empleadosDTO.setGenero(empleados.getGenero());
        empleadosDTO.setDireccion(empleados.getDireccion());
        empleadosDTO.setTelefono(empleados.getTelefono());
        empleadosDTO.setSalario(empleados.getSalario());
        empleadosDTO.setActivo(empleados.getActivo());
        empleadosDTO.setCodigo(empleados.getCodigo());
        return empleadosDTO;
    }

    private Empleados mapToEntity(final EmpleadosDTO empleadosDTO, final Empleados empleados) {
        empleados.setApellidos(empleadosDTO.getApellidos());
        empleados.setNombres(empleadosDTO.getNombres());
        empleados.setGenero(empleadosDTO.getGenero());
        empleados.setDireccion(empleadosDTO.getDireccion());
        empleados.setTelefono(empleadosDTO.getTelefono());
        empleados.setSalario(empleadosDTO.getSalario());
        empleados.setActivo(empleadosDTO.getActivo());
        empleados.setCodigo(empleadosDTO.getCodigo());
        return empleados;
    }

}
