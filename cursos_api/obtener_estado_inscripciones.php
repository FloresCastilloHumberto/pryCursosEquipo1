<?php
header("Content-Type: application/json; charset=UTF-8");

require_once "config/conexion.php";

$carreraId = isset($_GET["carrera_id"])
    ? intval($_GET["carrera_id"])
    : 0;

try {

    $sql = "SELECT
                c.idCurso AS id,
                c.codigoCurso AS codigo,
                c.nombreCurso AS nombre,
                c.imagenCurso AS url_imagen_principal,
                COUNT(i.idInscripcion) AS inscritos,
                c.cupoMaximo AS cupo_total
            FROM tblcursos c
            LEFT JOIN tblinscripciones i
                ON c.idCurso = i.idCurso
            WHERE (? = 0 OR c.idCarrera = ?)
            GROUP BY
                c.idCurso,
                c.codigoCurso,
                c.nombreCurso,
                c.imagenCurso,
                c.cupoMaximo
            ORDER BY c.idCurso";

    $stmt = $pdo->prepare($sql);
    $stmt->execute([$carreraId, $carreraId]);

    $cursos = $stmt->fetchAll(PDO::FETCH_ASSOC);

    $total = 0;

    foreach ($cursos as &$curso) {

        $curso["id"] = (int)$curso["id"];
        $curso["inscritos"] = (int)$curso["inscritos"];
        $curso["cupo_total"] = (int)$curso["cupo_total"];

        $total += $curso["inscritos"];
    }

    unset($curso);

    echo json_encode([
        "total" => $total,
        "cursos" => $cursos
    ], JSON_UNESCAPED_UNICODE);

} catch (Exception $e) {

    http_response_code(500);

    echo json_encode([
        "error" => "Error al obtener las inscripciones"
    ]);
}
?>