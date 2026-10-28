<?php

header("Content-Type: application/json; charset=utf-8");

require_once "config/conexion.php";

$matricula = $_GET["matricula"] ?? "";

if (empty($matricula)) {
    echo json_encode([
        "exito" => false,
        "correo" => "",
        "mensaje" => "Ingresa una matrícula"
    ]);
    exit;
}

try {

    $sql = "SELECT correo FROM tblalumnos WHERE matricula = :matricula";

    $consulta = $pdo->prepare($sql);

    $consulta->bindParam(":matricula", $matricula);

    $consulta->execute();

    $alumno = $consulta->fetch(PDO::FETCH_ASSOC);

    if ($alumno) {

        echo json_encode([
            "exito" => true,
            "correo" => $alumno["correo"],
            "mensaje" => "Alumno encontrado"
        ]);

    } else {

        echo json_encode([
            "exito" => false,
            "correo" => "",
            "mensaje" => "No se encontró la matrícula"
        ]);
    }

} catch (PDOException $e) {

    http_response_code(500);

    echo json_encode([
        "exito" => false,
        "correo" => "",
        "mensaje" => "Error al buscar al alumno"
    ]);
}

?>