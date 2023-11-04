package com.ra.parcial3.domain;

import com.ra.parcial3.model.Genero;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import java.math.BigDecimal;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class Empleados {

    @Id
    @Column(nullable = false, updatable = false)
    @SequenceGenerator(
            name = "primary_sequence",
            sequenceName = "primary_sequence",
            allocationSize = 1,
            initialValue = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "primary_sequence"
    )
    private Long id;

    @Column
    private String apellidos;

    @Column
    private String nombres;

    @Column
    @Enumerated(EnumType.STRING)
    private Genero genero;

    @Column
    private String direccion;

    @Column
    private String telefono;

    @Column
    private Double salario;

    @Column
    private Boolean activo = true;

    @Column
    private String codigo;

    @Column
    private String mensaje;

    public void setSalario(Double salario) {
        this.salario = calcularSalarioLiquido(salario).doubleValue();
    }

    public  BigDecimal calcularSalarioLiquido(double salario) {
        double isss = calcularISSS(salario);
        double afp = calcularAFP(salario);
        double renta = calcularRenta(salario, afp, isss);


        BigDecimal salarioLiquido = BigDecimal.valueOf(salario - (isss + afp + renta));

        return salarioLiquido;
    }

    public static double calcularISSS(double salario) {
        double resultado = salario * 0.03D;
        return Math.min(resultado, 30.0D);
    }

    public static double calcularAFP(double salario) {
        return salario * 0.0725D;
    }

    public static double calcularRenta(double salario, double afp, double isss) {
        double salarioGravado = salario - (isss + afp);

        if (salarioGravado < 472.00) {
            return 0.00;
        } else if (salarioGravado > 472.01 && salarioGravado < 895.24) {
            return ((salarioGravado - 472.00) * 0.10) + 17.67;
        } else if (salarioGravado > 895.25 && salarioGravado < 2038.10) {
            return ((salarioGravado - 895.24) * 0.20) + 60.00;
        } else { // TRAMO IV
            return ((salarioGravado - 2038.10) * 0.30) + 288.57;
        }
    }

}
