<?php
// detalle_curso.php

header("Content-Type: application/json; charset=UTF-8");
require_once "config/conexion.php";
$idCurso = isset($_GET['idCurso']) ? intval($_GET['idCurso']) : 0;

if ($idCurso <= 0) {
    header("HTTP/1.1 400 Bad Request");
    echo json_encode(["error" => "El ID del curso proporcionado no es válido"]);
    exit;
}

try {
    // 2. Consultamos la tabla tblcursos por el ID específico
    $stmt = $pdo->prepare("SELECT idCurso, idCarrera, nombreCurso, codigoCurso, descripcion, salon, totalHoras, diasAtencion, cupoMaximo, imagenCurso FROM tblcursos WHERE idCurso = ?");
    $stmt->execute([$idCurso]);
    $curso = $stmt->fetch(PDO::FETCH_ASSOC);

    if ($curso) {
        // 3. Convertimos los tipos numéricos para que coincidan con los tipos Int en Kotlin
        $curso['idCurso'] = (int)$curso['idCurso'];
        $curso['idCarrera'] = (int)$curso['idCarrera'];
        $curso['totalHoras'] = (int)$curso['totalHoras'];
        $curso['cupoMaximo'] = (int)$curso['cupoMaximo'];

        // 4. Retornamos el objeto JSON del curso
        echo json_encode($curso);
    } else {
        header("HTTP/1.1 404 Not Found");
        echo json_encode(["error" => "Curso no encontrado"]);
    }

} catch (Exception $e) {
    header("HTTP/1.1 500 Internal Server Error");
    echo json_encode(["error" => "Error al obtener el detalle del curso: " . $e->getMessage()]);
}
?>
