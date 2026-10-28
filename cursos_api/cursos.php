<?php
// cursos.php
header("Content-Type: application/json; charset=UTF-8");
require_once "config/conexion.php";

$idCarrera = isset($_GET['idCarrera'])
    ? intval($_GET['idCarrera'])
    : 0;

try {

    if ($idCarrera > 0) {

        $stmt = $pdo->prepare(
            "SELECT idCurso, idCarrera, nombreCurso,
                    codigoCurso, descripcion, salon,
                    totalHoras, diasAtencion, cupoMaximo,
                    imagenCurso
             FROM tblcursos
             WHERE idCarrera = ?"
        );

        $stmt->execute([$idCarrera]);

    } else {

        $stmt = $pdo->query(
            "SELECT idCurso, idCarrera, nombreCurso,
                    codigoCurso, descripcion, salon,
                    totalHoras, diasAtencion, cupoMaximo,
                    imagenCurso
             FROM tblcursos"
        );
    }

    $cursos = $stmt->fetchAll(PDO::FETCH_ASSOC);

    // Convertir tipos de datos para Kotlin
    foreach ($cursos as &$c) {

        $c['idCurso'] = (int)$c['idCurso'];
        $c['idCarrera'] = (int)$c['idCarrera'];

        $c['totalHoras'] = $c['totalHoras'] !== null
            ? (int)$c['totalHoras']
            : null;

        $c['cupoMaximo'] = (int)$c['cupoMaximo'];
    }

    unset($c);

    // Devolver directamente la lista de cursos
    echo json_encode($cursos, JSON_UNESCAPED_UNICODE);

} catch (Exception $e) {

    http_response_code(500);

    echo json_encode([
        "error" => "Error al obtener los cursos"
    ]);
}
?>
