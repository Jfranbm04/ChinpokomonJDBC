<?php 

include 'conexion.php';
error_reporting(E_ALL);
ini_set('display_errors', 1);

// Agrega las siguientes líneas para imprimir o loggear el contenido de $_POST
error_log('$_POST contents: ' . print_r($_POST, true));

// Resto de tu código sigue aquí...
$codigo = $_POST['codigo'];
$imagen = $_POST['imagen'];


// aqui escribimos codigo sq
$query = "INSERT INTO chinpokomon_fotos(codigo, imagen) values('$codigo', '$imagen')";
$resultado =mysqli_query($conexion,$query);

if($resultado){
    echo "foto insertada";
}else{
    echo "datos error";
}
mysqli_close($conexion);

?>