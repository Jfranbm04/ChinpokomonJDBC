<?php 

include 'conexion.php';

$codigo =$_POST['codigo'];
$nombre =$_POST['nombre'];
$nivel =$_POST['nivel'];
$tipo =$_POST['tipo'];
$movimiento =$_POST['movimiento'];


$query ="UPDATE chinpokomon SET codigo ='$codigo',nombre ='$nombre' ,nivel ='$nivel', tipo ='$tipo', movimiento ='$movimiento' WHERE codigo LIKE '$codigo'";

$resultado =mysqli_query($conexion,$query);

if($resultado){
    echo "datos actualizados";
}else{
    echo "error en actualizacion";
}


mysqli_close($conexion);

?>