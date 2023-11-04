package com.ra.parcial3.rest;

import com.ra.parcial3.model.DepartamentosDTO;
import com.ra.parcial3.service.DepartamentosService;

import javax.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/api/departamentos", produces = MediaType.APPLICATION_JSON_VALUE)
public class DepartamentosResource {

    private final DepartamentosService departamentosService;

    public DepartamentosResource(final DepartamentosService departamentosService) {
        this.departamentosService = departamentosService;
    }

    @GetMapping
    public ResponseEntity<List<DepartamentosDTO>> getAllDepartamentoss() {
        return ResponseEntity.ok(departamentosService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepartamentosDTO> getDepartamentos(
            @PathVariable(name = "id") final Long id) {
        return ResponseEntity.ok(departamentosService.get(id));
    }

    @PostMapping

    public ResponseEntity<Long> createDepartamentos(
            @RequestBody @Valid final DepartamentosDTO departamentosDTO) {
        final Long createdId = departamentosService.create(departamentosDTO);
        return new ResponseEntity<>(createdId, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Long> updateDepartamentos(@PathVariable(name = "id") final Long id,
            @RequestBody @Valid final DepartamentosDTO departamentosDTO) {
        departamentosService.update(id, departamentosDTO);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/{id}")

    public ResponseEntity<Void> deleteDepartamentos(@PathVariable(name = "id") final Long id) {
        departamentosService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
