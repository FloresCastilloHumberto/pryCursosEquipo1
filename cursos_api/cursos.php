<?php
header("Content-Type: application/json; charset=UTF-8");
require_once "config/conexion.php";

try {
    // Consulta adaptada a los campos reales de tu base de datos
    $stmt = $pdo->query("SELECT idCurso, idCarrera, nombreCurso, codigoCurso, descripcion, salon, totalHoras, diasAtencion, cupoMaximo, imagenCurso FROM tblcursos");
    $cursos = $stmt->fetchAll(PDO::FETCH_ASSOC);

    echo json_encode([
        "exito" => true,
        "cursos" => $cursos
    ]);
} catch (Exception $e) {
    header("HTTP/1.1 500 Internal Server Error");
    echo json_encode([
        "exito" => false,
        "mensaje" => "Error en el servidor: " . $e->getMessage()
    ]);
}
?>