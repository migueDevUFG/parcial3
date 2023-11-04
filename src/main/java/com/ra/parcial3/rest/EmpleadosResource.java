package com.ra.parcial3.rest;

import com.ra.parcial3.model.EmpleadosDTO;
import com.ra.parcial3.service.EmpleadosService;

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
@RequestMapping(value = "/api/empleados", produces = MediaType.APPLICATION_JSON_VALUE)
public class EmpleadosResource {

    private final EmpleadosService empleadosService;

    public EmpleadosResource(final EmpleadosService empleadosService) {
        this.empleadosService = empleadosService;
    }

    @GetMapping
    public ResponseEntity<List<EmpleadosDTO>> getAllEmpleadoss() {
        return ResponseEntity.ok(empleadosService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmpleadosDTO> getEmpleados(@PathVariable(name = "id") final Long id) {
        return ResponseEntity.ok(empleadosService.get(id));
    }

    @PostMapping

    public ResponseEntity<Long> createEmpleados(
            @RequestBody @Valid final EmpleadosDTO empleadosDTO) {
        final Long createdId = empleadosService.create(empleadosDTO);
        return new ResponseEntity<>(createdId, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Long> updateEmpleados(@PathVariable(name = "id") final Long id,
                                                @RequestBody @Valid final EmpleadosDTO empleadosDTO) {
        empleadosService.update(id, empleadosDTO);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/{id}")

    public ResponseEntity<Void> deleteEmpleados(@PathVariable(name = "id") final Long id) {
        empleadosService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
