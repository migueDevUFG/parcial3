package com.ra.parcial3.rest;

import com.ra.parcial3.domain.EmpleadosInactivos;
import com.ra.parcial3.model.EmpleadosDTO;
import com.ra.parcial3.repos.EmpleadosInactivosRepository;
import com.ra.parcial3.service.EmpleadosService;
import com.ra.parcial3.domain.Empleados;
import com.ra.parcial3.repos.EmpleadosRepository;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/api/empleados", produces = MediaType.APPLICATION_JSON_VALUE)
public class EmpleadosResource {

    private final EmpleadosService empleadosService;
    private final EmpleadosInactivosRepository empleadosInactivosRepository;

    public EmpleadosResource(final EmpleadosService empleadosService,
                             final EmpleadosInactivosRepository empleadosInactivosRepository) {
        this.empleadosService = empleadosService;
        this.empleadosInactivosRepository = empleadosInactivosRepository;
    }

    @GetMapping
    public ResponseEntity<List<EmpleadosDTO>> getAllEmpleadoss() {
        return ResponseEntity.ok(empleadosService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmpleadosDTO> getEmpleados(@PathVariable(name = "id") final Long id) {
        return ResponseEntity.ok(empleadosService.get(id));
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

    @PostMapping
    public ResponseEntity<Long> createEmpleados(
            @RequestBody @Valid final EmpleadosDTO empleadosDTO) {
        empleadosDTO.setSalario(null);
        empleadosDTO.setCodigo(null);

        final Long createdId = empleadosService.create(empleadosDTO);
        return new ResponseEntity<>(createdId, HttpStatus.CREATED);
    }

    @PutMapping("/{id}/asignar-salario-codigo")
    public ResponseEntity<EmpleadosDTO> assignSalaryAndCode(
            @PathVariable(name = "id") final Long id,
            @RequestParam Double salario,
            @RequestParam String codigo) {

        EmpleadosDTO empleadosDTO = empleadosService.get(id);

        if (empleadosDTO != null) {
            empleadosDTO.setSalario(calcularSalarioNeto(salario));
            empleadosDTO.setCodigo(codigo);
            empleadosService.update(id, empleadosDTO);
            return ResponseEntity.ok(empleadosDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //Metodos para calcular el salario neto
    public static double calcularSalarioNeto(double salarioBruto) {
        double renta = calcularRenta(salarioBruto);
        double AFP = calcularAFP(salarioBruto);
        double ISSS = calcularISSS(salarioBruto);

        double salarioNeto = salarioBruto - renta - AFP - ISSS;

        return salarioNeto;
    }
    public static double calcularRenta(double salarioBruto) {
        double renta = 0;

        return renta;
    }
    public static double calcularAFP(double salarioBruto) {
        double AFP = salarioBruto * 0.0725;

        return AFP;
    }
    public static double calcularISSS(double salarioBruto) {
        double ISSS = salarioBruto * 0.03;

        return ISSS;
    }

    @PostMapping("/{id}/inactivar")
    public ResponseEntity<Void> inactivarEmpleado(
            @PathVariable(name = "id") final Long id,
            @RequestBody Map<String, String> motivoJson) {
        EmpleadosDTO empleadoDTO = empleadosService.get(id);
        if (empleadoDTO != null) {
            empleadoDTO.setActivo(false);
            empleadosService.update(id, empleadoDTO);
            String motivo = motivoJson.get("motivo");

            EmpleadosInactivos empleadoInactivo = new EmpleadosInactivos();
            empleadoInactivo.setNombre(empleadoDTO.getNombres() + " " + empleadoDTO.getApellidos());
            empleadoInactivo.setMotivo(motivo);
            empleadosInactivosRepository.save(empleadoInactivo);

            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
