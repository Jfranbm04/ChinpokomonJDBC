<?php 

include 'conexion.php';

$codigo = $_POST["codigo"];
$query = "DELETE FROM chinpokomon WHERE codigo LIKE '$codigo'";
$result=mysqli_query($conexion,$query);
if($result){
    echo "datos eliminados";
}else{
    echo "error";
}

mysqli_close($conexion);

?>