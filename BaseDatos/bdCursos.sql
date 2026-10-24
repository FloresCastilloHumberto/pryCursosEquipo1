-- ==========================================
-- BASE DE DATOS: bdCursos
-- ==========================================

CREATE DATABASE IF NOT EXISTS bdCursos
CHARACTER SET utf8mb4
COLLATE utf8mb4_unicode_ci;

USE bdCursos;

-- ==========================================
-- ELIMINAR TABLAS EXISTENTES
-- Primero las tablas dependientes
-- ==========================================

DROP TABLE IF EXISTS tblinscripciones;
DROP TABLE IF EXISTS tblcursos;
DROP TABLE IF EXISTS tblalumnos;
DROP TABLE IF EXISTS tblcarreras;

-- ==========================================
-- 1. TABLA DE CARRERAS
-- ==========================================

CREATE TABLE tblcarreras (
    idCarrera INT AUTO_INCREMENT PRIMARY KEY,
    nombreCarrera VARCHAR(100) NOT NULL
) ENGINE=INNODB;

-- ==========================================
-- 2. TABLA DE CURSOS
-- ==========================================

CREATE TABLE tblcursos (
    idCurso INT AUTO_INCREMENT PRIMARY KEY,
    idCarrera INT NOT NULL,
    nombreCurso VARCHAR(100) NOT NULL,
    codigoCurso VARCHAR(20),
    descripcion TEXT,
    salon VARCHAR(50),
    totalHoras INT,
    diasAtencion VARCHAR(100),
    cupoMaximo INT NOT NULL,
    imagenCurso VARCHAR(255),

    CONSTRAINT fk_curso_carrera
        FOREIGN KEY (idCarrera)
        REFERENCES tblcarreras(idCarrera)
) ENGINE=INNODB;

-- ==========================================
-- 3. TABLA DE ALUMNOS
-- ==========================================

CREATE TABLE tblalumnos (
    matricula VARCHAR(20) PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    grado VARCHAR(2) NOT NULL,
    grupo VARCHAR(2) NOT NULL,
    telefono VARCHAR(10),
    correo VARCHAR(150) NOT NULL UNIQUE
) ENGINE=INNODB;

-- ==========================================
-- 4. TABLA DE INSCRIPCIONES
-- ==========================================

CREATE TABLE tblinscripciones (
    idInscripcion INT AUTO_INCREMENT PRIMARY KEY,
    matricula VARCHAR(20) NOT NULL,
    idCurso INT NOT NULL,
    fechaRegistro DATE NOT NULL,

    CONSTRAINT fk_inscripcion_alumno
        FOREIGN KEY (matricula)
        REFERENCES tblalumnos(matricula),

    CONSTRAINT fk_inscripcion_curso
        FOREIGN KEY (idCurso)
        REFERENCES tblcursos(idCurso),

    CONSTRAINT uq_alumno_curso
        UNIQUE (matricula, idCurso)
) ENGINE=InnoDB;

-- ==========================================
-- 5. INSERTAR CARRERAS
-- ==========================================

INSERT INTO tblcarreras (nombreCarrera)
VALUES
('INGENIERÍA EN SISTEMAS COMPUTACIONALES'),
('INGENIERÍA EN MECATRÓNICA'),
('INGENIERÍA EN ALIMENTOS');

-- ==========================================
-- 6. INSERTAR CURSOS
-- ==========================================

INSERT INTO tblcursos (
    idCarrera,
    nombreCurso,
    codigoCurso,
    descripcion,
    salon,
    totalHoras,
    diasAtencion,
    cupoMaximo,
    imagenCurso
)
VALUES

-- SISTEMAS COMPUTACIONALES

(1, 'PROGRAMACIÓN AVANZADA', 'SIS101',
'Programación orientada a objetos y estructuras de datos.',
'Laboratorio A-101', 80,
'Lunes y Miércoles 08:00-10:00', 50,
'programacion.jpg'),

(1, 'BASES DE DATOS II', 'SIS102',
'Diseño y administración de bases de datos relacionales.',
'Laboratorio A-102', 60,
'Martes y Jueves 10:00-12:00', 45,
'basedatos.jpg'),

(1, 'INTELIGENCIA ARTIFICIAL', 'SIS103',
'Fundamentos de inteligencia artificial y aprendizaje automático.',
'Laboratorio A-103', 80,
'Viernes 08:00-12:00', 40,
'inteligencia.jpg'),

(1, 'REDES DE COMPUTADORAS', 'SIS104',
'Configuración de redes y protocolos de comunicación.',
'Laboratorio A-104', 60,
'Lunes y Miércoles 10:00-12:00', 50,
'redes.jpg'),

(1, 'DESARROLLO WEB', 'SIS105',
'Desarrollo de aplicaciones web con HTML, CSS y JavaScript.',
'Laboratorio A-105', 80,
'Martes y Jueves 12:00-14:00', 40,
'desarrolloweb.jpg'),

(1, 'DESARROLLO DE APLICACIONES MÓVILES', 'SIS106',
'Creación de aplicaciones Android utilizando Kotlin.',
'Laboratorio A-106', 80,
'Viernes 09:00-13:00', 35,
'moviles.jpg'),

-- MECATRÓNICA

(2, 'ROBÓTICA INDUSTRIAL', 'MEC101',
'Programación y operación de robots industriales.',
'Laboratorio B-101', 80,
'Lunes y Miércoles 08:00-10:00', 35,
'robotica.jpg'),

(2, 'AUTOMATIZACIÓN INDUSTRIAL', 'MEC102',
'Automatización de procesos mediante controladores programables.',
'Laboratorio B-102', 80,
'Martes y Jueves 10:00-12:00', 40,
'automatizacion.jpg'),

(2, 'ELECTRÓNICA DIGITAL', 'MEC103',
'Diseño y análisis de circuitos electrónicos digitales.',
'Laboratorio B-103', 60,
'Viernes 08:00-12:00', 35,
'electronica.jpg'),

(2, 'SISTEMAS NEUMÁTICOS', 'MEC104',
'Funcionamiento de sistemas neumáticos industriales.',
'Laboratorio B-104', 60,
'Lunes y Miércoles 10:00-12:00', 40,
'neumatica.jpg'),

(2, 'CONTROL AUTOMÁTICO', 'MEC105',
'Fundamentos de sistemas de control y automatización.',
'Laboratorio B-105', 80,
'Martes y Jueves 12:00-14:00', 35,
'control.jpg'),

(2, 'DISEÑO MECÁNICO', 'MEC106',
'Diseño de componentes mecánicos mediante software CAD.',
'Laboratorio B-106', 60,
'Viernes 09:00-12:00', 40,
'disenomecanico.jpg'),

-- ALIMENTOS

(3, 'MICROBIOLOGÍA DE ALIMENTOS', 'ALI101',
'Estudio de microorganismos presentes en los alimentos.',
'Laboratorio C-101', 80,
'Lunes y Miércoles 08:00-10:00', 30,
'microbiologia.jpg'),

(3, 'CONSERVACIÓN DE ALIMENTOS', 'ALI102',
'Técnicas de conservación y procesamiento de alimentos.',
'Laboratorio C-102', 60,
'Martes y Jueves 10:00-12:00', 35,
'conservacion.jpg'),

(3, 'INOCUIDAD ALIMENTARIA', 'ALI103',
'Normas y procedimientos para garantizar alimentos seguros.',
'Laboratorio C-103', 60,
'Viernes 09:00-12:00', 40,
'inocuidad.jpg'),

(3, 'TECNOLOGÍA DE LÁCTEOS', 'ALI104',
'Procesamiento y elaboración de productos lácteos.',
'Laboratorio C-104', 80,
'Lunes y Miércoles 10:00-12:00', 30,
'lacteos.jpg'),

(3, 'ANÁLISIS DE ALIMENTOS', 'ALI105',
'Análisis físico y químico de productos alimenticios.',
'Laboratorio C-105', 60,
'Martes y Jueves 12:00-14:00', 35,
'analisis.jpg'),

(3, 'PROCESAMIENTO DE CÁRNICOS', 'ALI106',
'Técnicas de procesamiento y conservación de productos cárnicos.',
'Laboratorio C-106', 80,
'Viernes 08:00-12:00', 30,
'carnicos.jpg');

-- ==========================================
-- 7. INSERTAR ALUMNOS
-- ==========================================

INSERT INTO tblalumnos (
    matricula,
    nombre,
    grado,
    grupo,
    telefono,
    correo
)
VALUES
('20240001', 'Juan Pérez', '4', 'A', '7711234567', 'juan.perez@mail.com'),
('20240002', 'María López', '4', 'A', '7711234568', 'maria.lopez@mail.com'),
('20240003', 'Carlos García', '4', 'B', '7711234569', 'carlos.garcia@mail.com'),
('20240004', 'Ana Martínez', '4', 'B', '7711234570', 'ana.martinez@mail.com'),
('20240005', 'Luis Hernández', '5', 'A', '7711234571', 'luis.hernandez@mail.com'),
('20240006', 'Sofía Ramírez', '5', 'A', '7711234572', 'sofia.ramirez@mail.com'),
('20240007', 'Pedro Torres', '5', 'B', '7711234573', 'pedro.torres@mail.com'),
('20240008', 'Laura Sánchez', '5', 'B', '7711234574', 'laura.sanchez@mail.com'),
('20240009', 'Diego Flores', '6', 'A', '7711234575', 'diego.flores@mail.com'),
('20240010', 'Elena Cruz', '6', 'A', '7711234576', 'elena.cruz@mail.com'),
('20240011', 'Miguel Reyes', '6', 'B', '7711234577', 'miguel.reyes@mail.com'),
('20240012', 'Andrea Mendoza', '6', 'B', '7711234578', 'andrea.mendoza@mail.com'),
('20240013', 'Jorge Castillo', '4', 'A', '7711234579', 'jorge.castillo@mail.com'),
('20240014', 'Valeria Romero', '4', 'B', '7711234580', 'valeria.romero@mail.com'),
('20240015', 'Fernando Vargas', '5', 'A', '7711234581', 'fernando.vargas@mail.com'),
('20240016', 'Daniela Ortiz', '5', 'B', '7711234582', 'daniela.ortiz@mail.com'),
('20240017', 'Ricardo Morales', '6', 'A', '7711234583', 'ricardo.morales@mail.com'),
('20240018', 'Paola Navarro', '6', 'B', '7711234584', 'paola.navarro@mail.com'),
('20240019', 'Eduardo Silva', '4', 'A', '7711234585', 'eduardo.silva@mail.com'),
('20240020', 'Gabriela Ramos', '4', 'B', '7711234586', 'gabriela.ramos@mail.com');

-- ==========================================
-- 8. INSERTAR INSCRIPCIONES
-- ==========================================

INSERT INTO tblinscripciones (
    matricula,
    idCurso,
    fechaRegistro
)
VALUES

-- SISTEMAS COMPUTACIONALES

('20240001', 1, '2026-10-01'),
('20240002', 1, '2026-10-01'),
('20240003', 1, '2026-10-02'),
('20240004', 2, '2026-10-02'),
('20240005', 2, '2026-10-03'),
('20240006', 3, '2026-10-03'),
('20240007', 3, '2026-10-04'),
('20240008', 4, '2026-10-04'),
('20240009', 4, '2026-10-05'),
('20240010', 5, '2026-10-05'),
('20240011', 5, '2026-10-06'),
('20240012', 6, '2026-10-06'),
('20240013', 6, '2026-10-07'),

-- MECATRÓNICA

('20240014', 7, '2026-10-07'),
('20240015', 7, '2026-10-08'),
('20240016', 8, '2026-10-08'),
('20240017', 8, '2026-10-09'),
('20240018', 9, '2026-10-09'),
('20240019', 9, '2026-10-10'),
('20240020', 10, '2026-10-10'),
('20240001', 10, '2026-10-11'),
('20240002', 11, '2026-10-11'),
('20240003', 11, '2026-10-12'),
('20240004', 12, '2026-10-12'),
('20240005', 12, '2026-10-13'),

-- ALIMENTOS

('20240006', 13, '2026-10-13'),
('20240007', 13, '2026-10-14'),
('20240008', 14, '2026-10-14'),
('20240009', 14, '2026-10-15'),
('20240010', 15, '2026-10-15'),
('20240011', 15, '2026-10-16'),
('20240012', 16, '2026-10-16'),
('20240013', 16, '2026-10-17'),
('20240014', 17, '2026-10-17'),
('20240015', 17, '2026-10-18'),
('20240016', 18, '2026-10-18'),
('20240017', 18, '2026-10-19'),

-- INSCRIPCIONES ADICIONALES

('20240018', 1, '2026-10-19'),
('20240019', 7, '2026-10-20'),
('20240020', 13, '2026-10-20');

-- ==========================================
-- 9. VERIFICAR REGISTROS
-- ==========================================

SELECT 'Carreras' AS tabla, COUNT(*) AS total
FROM tblcarreras

UNION ALL

SELECT 'Cursos', COUNT(*)
FROM tblcursos

UNION ALL

SELECT 'Alumnos', COUNT(*)
FROM tblalumnos

UNION ALL

SELECT 'Inscripciones', COUNT(*)
FROM tblinscripciones;
