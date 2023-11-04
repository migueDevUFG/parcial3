package com.ra.parcial3.rest;

import com.ra.parcial3.model.MunicipiosDTO;
import com.ra.parcial3.service.MunicipiosService;

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
@RequestMapping(value = "/api/municipios", produces = MediaType.APPLICATION_JSON_VALUE)
public class MunicipiosResource {

    private final MunicipiosService municipiosService;

    public MunicipiosResource(final MunicipiosService municipiosService) {
        this.municipiosService = municipiosService;
    }

    @GetMapping
    public ResponseEntity<List<MunicipiosDTO>> getAllMunicipioss() {
        return ResponseEntity.ok(municipiosService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MunicipiosDTO> getMunicipios(@PathVariable(name = "id") final Long id) {
        return ResponseEntity.ok(municipiosService.get(id));
    }

    @PostMapping

    public ResponseEntity<Long> createMunicipios(
            @RequestBody @Valid final MunicipiosDTO municipiosDTO) {
        final Long createdId = municipiosService.create(municipiosDTO);
        return new ResponseEntity<>(createdId, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Long> updateMunicipios(@PathVariable(name = "id") final Long id,
                                                 @RequestBody @Valid final MunicipiosDTO municipiosDTO) {
        municipiosService.update(id, municipiosDTO);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/{id}")

    public ResponseEntity<Void> deleteMunicipios(@PathVariable(name = "id") final Long id) {
        municipiosService.delete(id);
        return ResponseEntity.noContent().build();
    }

}

