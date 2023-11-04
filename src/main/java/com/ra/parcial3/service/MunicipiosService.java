package com.ra.parcial3.service;

import com.ra.parcial3.domain.Departamentos;
import com.ra.parcial3.domain.Municipios;
import com.ra.parcial3.model.MunicipiosDTO;
import com.ra.parcial3.repos.DepartamentosRepository;
import com.ra.parcial3.repos.MunicipiosRepository;
import com.ra.parcial3.util.NotFoundException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class MunicipiosService {

    private final MunicipiosRepository municipiosRepository;
    private final DepartamentosRepository departamentosRepository;

    public MunicipiosService(final MunicipiosRepository municipiosRepository,
                             final DepartamentosRepository departamentosRepository) {
        this.municipiosRepository = municipiosRepository;
        this.departamentosRepository = departamentosRepository;
    }

    public List<MunicipiosDTO> findAll() {
        final List<Municipios> municipioses = municipiosRepository.findAll(Sort.by("id"));
        return municipioses.stream()
                .map(municipios -> mapToDTO(municipios, new MunicipiosDTO()))
                .collect(Collectors.toList());
    }

    public MunicipiosDTO get(final Long id) {
        return municipiosRepository.findById(id)
                .map(municipios -> mapToDTO(municipios, new MunicipiosDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Long create(final MunicipiosDTO municipiosDTO) {
        final Municipios municipios = new Municipios();
        mapToEntity(municipiosDTO, municipios);
        return municipiosRepository.save(municipios).getId();
    }

    public void update(final Long id, final MunicipiosDTO municipiosDTO) {
        final Municipios municipios = municipiosRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(municipiosDTO, municipios);
        municipiosRepository.save(municipios);
    }

    public void delete(final Long id) {
        municipiosRepository.deleteById(id);
    }

    private MunicipiosDTO mapToDTO(final Municipios municipios, final MunicipiosDTO municipiosDTO) {
        municipiosDTO.setId(municipios.getId());
        municipiosDTO.setNombreMunicipio(municipios.getNombreMunicipio());
        municipiosDTO.setDepartamentos(municipios.getDepartamentos() == null ? null : municipios.getDepartamentos().getId());
        return municipiosDTO;
    }

    private Municipios mapToEntity(final MunicipiosDTO municipiosDTO, final Municipios municipios) {
        municipios.setNombreMunicipio(municipiosDTO.getNombreMunicipio());
        final Departamentos departamentos = municipiosDTO.getDepartamentos() == null ? null : departamentosRepository.findById(municipiosDTO.getDepartamentos())
                .orElseThrow(() -> new NotFoundException("departamentos not found"));
        municipios.setDepartamentos(departamentos);
        return municipios;
    }

}
