<?php
header("Content-Type: application/json; charset=UTF-8");
require_once "config/conexion.php";

// Recibir los datos enviados por JSON
$jsonInput = file_get_contents("php://input");
$data = json_decode($jsonInput, true);

$nombreUsuario = isset($data['nombreUsuario']) ? trim($data['nombreUsuario']) : '';
$passusuario = isset($data['passusuario']) ? trim($data['passusuario']) : '';
$nombreCliente = isset($data['nombreCliente']) ? trim($data['nombreCliente']) : '';
$telefono = isset($data['telefono']) ? trim($data['telefono']) : '';

// Validar que los campos obligatorios vengan llenos
if (empty($nombreUsuario) || empty($passusuario) || empty($nombreCliente) || empty($telefono)) {
    header("HTTP/1.1 400 Bad Request");
    echo json_encode([
        "exito" => false,
        "mensaje" => "Todos los campos son obligatorios"
    ]);
    exit;
}

try {
    // Verificar si el nombre de usuario ya existe
    $stmtCheck = $pdo->prepare("SELECT idUsuario FROM tblusuarios WHERE nombreUsuario = ?");
    $stmtCheck->execute([$nombreUsuario]);

    if ($stmtCheck->rowCount() > 0) {
        header("HTTP/1.1 409 Conflict");
        echo json_encode([
            "exito" => false,
            "mensaje" => "El usuario ya se encuentra registrado"
        ]);
        exit;
    }

    // Insertar el nuevo usuario en la base de datos
    $stmt = $pdo->prepare("INSERT INTO tblusuarios (nombreUsuario, passusuario, nombreCliente, telefono) VALUES (?, ?, ?, ?)");
    $resultado = $stmt->execute([$nombreUsuario, $passusuario, $nombreCliente, $telefono]);

    if ($resultado) {
        echo json_encode([
            "exito" => true,
            "mensaje" => "Usuario registrado correctamente"
        ]);
    } else {
        echo json_encode([
            "exito" => false,
            "mensaje" => "No se pudo completar el registro"
        ]);
    }

} catch (Exception $e) {
    header("HTTP/1.1 500 Internal Server Error");
    echo json_encode([
        "exito" => false,
        "mensaje" => "Error en el servidor: " . $e->getMessage()
    ]);
}
?>