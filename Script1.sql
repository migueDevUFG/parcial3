USE parcial3;
-- Insertar el primer empleado
INSERT INTO Empleados (apellidos, nombres, genero, direccion, telefono, salario, activo)
VALUES ('López', 'Juan', 'MASCULINO', 'Calle Principal 123', '123-456-7890', 45000.0, 1);

-- Insertar el segundo empleado
INSERT INTO Empleados (apellidos, nombres, genero, direccion, telefono, salario, activo)
VALUES ('Martínez', 'Ana', 'FEMENINO', 'Avenida Secundaria 456', '987-654-3210', 52000.0, 1);



-- Insertar los departamentos de El Salvador
INSERT INTO Departamentos (nombre_departamento) VALUES
('Ahuachapán'),
('Cabañas'),
('Chalatenango'),
('Cuscatlán'),
('La Libertad'),
('La Paz'),
('La Unión'),
('Morazán'),
('San Miguel'),
('San Salvador'),
('San Vicente'),
( 'Santa Ana'),
('Sonsonate'),
( 'Usulután');


INSERT INTO Municipios (nombre_municipio, id_departamento_id) VALUES
('Santa Ana', 12),         -- Municipio en el departamento de Santa Ana (id = 12)
('San Salvador', 10),      -- Municipio en el departamento de San Salvador (id = 10)
('San Miguel', 9),         -- Municipio en el departamento de San Miguel (id = 9)
('Apopa', 10),             -- Municipio en el departamento de San Salvador (id = 10)
('Ahuachapán', 1),         -- Municipio en el departamento de Ahuachapán (id = 1)
('Usulután', 14);          -- Municipio en el departamento de Usulután (id = 14)