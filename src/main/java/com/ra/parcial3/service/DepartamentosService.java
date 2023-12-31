package com.ra.parcial3.service;

import com.ra.parcial3.domain.Departamentos;
import com.ra.parcial3.domain.Empleados;
import com.ra.parcial3.domain.Municipios;
import com.ra.parcial3.model.DepartamentosDTO;
import com.ra.parcial3.model.MunicipiosDTO;
import com.ra.parcial3.repos.DepartamentosRepository;
import com.ra.parcial3.repos.EmpleadosRepository;
import com.ra.parcial3.util.NotFoundException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class DepartamentosService {

    private final DepartamentosRepository departamentosRepository;

    public DepartamentosService(final DepartamentosRepository departamentosRepository) {
        this.departamentosRepository = departamentosRepository;
    }

    public List<DepartamentosDTO> findAll() {
        final List<Departamentos> departamentoses = departamentosRepository.findAll(Sort.by("id"));
        return departamentoses.stream()
                .map(departamentos -> mapToDTO(departamentos, new DepartamentosDTO()))
                .collect(Collectors.toList());
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
        return departamentosDTO;
    }

    private Departamentos mapToEntity(final DepartamentosDTO departamentosDTO,
                                      final Departamentos departamentos) {
        departamentos.setNombreDepartamento(departamentosDTO.getNombreDepartamento());
        return departamentos;
    }

    public boolean nombreDepartamentoExists(final String nombreDepartamento) {
        return departamentosRepository.existsByNombreDepartamentoIgnoreCase(nombreDepartamento);
    }

    public List<MunicipiosDTO> getMunicipiosByDepartamento(Long departamentoId) {
        Departamentos departamento = departamentosRepository.findById(departamentoId)
                .orElseThrow(NotFoundException::new);

        Set<Municipios> municipios = departamento.getMunicipios();
        return municipios.stream()
                .map(this::mapMunicipiosToDTO)
                .collect(Collectors.toList());
    }

    private MunicipiosDTO mapMunicipiosToDTO(Municipios municipio) {
        MunicipiosDTO municipioDTO = new MunicipiosDTO();
        municipioDTO.setId(municipio.getId());
        municipioDTO.setNombreMunicipio(municipio.getNombreMunicipio());
        municipioDTO.setDepartamentos(municipio.getDepartamentos().getId());
        return municipioDTO;
    }

}
