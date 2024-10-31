
drop schema if exists farmacia;
drop user if exists usuario_prueba;

-- Crear la base de datos
CREATE SCHEMA farmacia;

-- Crear el usuario y otorgarle permisos
CREATE USER 'usuario_prueba'@'%' IDENTIFIED BY 'Usuar1o_Clave.';
GRANT ALL PRIVILEGES ON farmacia.* TO 'usuario_prueba'@'%';
FLUSH PRIVILEGES;

-- Seleccionar la base de datos
USE farmacia;

-- Crear tabla Categoria
CREATE TABLE categoria (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    descripcion VARCHAR(255)
);

-- Crear tabla Medicamentos
CREATE TABLE medicamentos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    descripcion TEXT,
    precio DECIMAL(10, 2) NOT NULL,
    stock INT DEFAULT 0,
    categoria nvarchar(200),
    FOREIGN KEY (id_categoria) REFERENCES categoria(id) ON DELETE SET NULL
);



-- Crear tabla Clientes
CREATE TABLE clientes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    apellido VARCHAR(100),
    telefono VARCHAR(20),
    email VARCHAR(100),
    direccion VARCHAR(255)
);

-- Crear tabla Facturas
CREATE TABLE facturas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    fecha DATE NOT NULL,
    id_cliente INT,
    total DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (id_cliente) REFERENCES clientes(id) ON DELETE SET NULL
);

-- Crear tabla Factura_Detalle para detallar cada medicamento en la factura
CREATE TABLE factura_detalle (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_factura INT,
    id_medicamento INT,
    cantidad INT NOT NULL,
    precio_unitario DECIMAL(10, 2) NOT NULL,
    subtotal DECIMAL(10, 2) GENERATED ALWAYS AS (cantidad * precio_unitario) STORED,
    FOREIGN KEY (id_factura) REFERENCES facturas(id) ON DELETE CASCADE,
    FOREIGN KEY (id_medicamento) REFERENCES medicamentos(id) ON DELETE RESTRICT
);

-- Insertar registros en Categoria
INSERT INTO categoria (nombre, descripcion) VALUES 
('Analgésicos', 'Medicamentos para aliviar el dolor'),
('Vitaminas', 'Suplementos vitamínicos y minerales'),
('Antibióticos', 'Medicamentos para tratar infecciones');

-- Insertar registros en Medicamentos
INSERT INTO medicamentos (nombre, descripcion, precio, stock, categoria) VALUES 
('Paracetamol', 'Analgésico y antipirético para aliviar dolores leves y moderados', 2.50, 100, 'Pastilla'),
('Ibuprofeno', 'Antiinflamatorio para reducir dolor y fiebre', 3.00, 80, 'Pastilla'),
('Vitamina C', 'Suplemento para fortalecer el sistema inmunológico', 1.50, 200, 'Pastilla'),
('Amoxicilina', 'Antibiótico de amplio espectro', 4.00, 50, 'Pastilla');

-- Insertar registros en Clientes
INSERT INTO clientes (nombre, apellido, telefono, email, direccion) VALUES 
('Juan', 'Perez', '123456789', 'juan.perez@example.com', 'Calle Falsa 123'),
('Maria', 'Gomez', '987654321', 'maria.gomez@example.com', 'Avenida Siempre Viva 456');

-- Insertar registros en Facturas
INSERT INTO facturas (fecha, id_cliente, total) VALUES 
('2024-10-30', 1, 6.50),
('2024-10-31', 2, 4.00);

-- Insertar registros en Factura_Detalle
INSERT INTO factura_detalle (id_factura, id_medicamento, cantidad, precio_unitario) VALUES 
(1, 1, 2, 2.50),  -- Paracetamol x2
(1, 3, 1, 1.50),  -- Vitamina C x1
(2, 4, 1, 4.00);  -- Amoxicilina x1


ALTER TABLE MEDICAMENTOS
DROP FOREIGN KEY medicamentos_ibfk_1;

ALTER TABLE factura_detalle
DROP FOREIGN KEY factura_detalle_ibfk_2;

ALTER TABLE MEDICAMENTOS
Drop column id_categoria;

ALTER TABLE MEDICAMENTOS
ADD COLUMN categoria nvarchar(200)
DEFAULT 'Pastillas';

CREATE TABLE  sugerencia (
    id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    nombre_persona VARCHAR(255) NOT NULL,
    sugerencia TEXT NOT NULL
);

DROP TABLE sugerencia;

INSERT INTO sugerencia VALUES (1, 'Elias Viquez', 'La pagina es muy linda');

INSERT INTO sugerencia VALUES ('Elias Viquez', 'Profe guapa');