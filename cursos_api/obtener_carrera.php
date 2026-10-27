<?php
// obtenerCarrera.php

header("Content-Type: application/json; charset=UTF-8");

require_once __DIR__ . "/config/conexion.php";

try {
    // 1. Consultar las carreras registradas
    $stmt = $pdo->prepare(
        "SELECT idCarrera, nombreCarrera FROM tblcarreras"
    );

    // 2. Ejecutar la consulta
    $stmt->execute();

    // 3. Obtener todas las carreras
    $carreras = $stmt->fetchAll(PDO::FETCH_ASSOC);

    // 4. Convertir idCarrera a entero para Kotlin
    foreach ($carreras as &$c) {
        $c['idCarrera'] = (int) $c['idCarrera'];
    }
    unset($c);

    // 5. Devolver los datos en formato JSON
    echo json_encode(
        $carreras,
        JSON_UNESCAPED_UNICODE
    );

} catch (PDOException $e) {

    http_response_code(500);

    echo json_encode([
        "error" => "Error al obtener las carreras",
        "detalle" => $e->getMessage()
    ]);
}
?>
