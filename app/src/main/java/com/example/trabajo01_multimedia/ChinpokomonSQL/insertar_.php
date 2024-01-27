<?php 

include 'conexion.php';
$codigo =$_POST['codigo'];
$nombre =$_POST['nombre'];
$nivel =$_POST['nivel'];
$tipo =$_POST['tipo'];
$movimiento =$_POST['movimiento'];


// aqui escribimos codigo sql
$query ="INSERT INTO chinpokomon (codigo,nombre,nivel,tipo,movimiento) values('$codigo' ,'$nombre', '$nivel', '$tipo', '$movimiento') ";
$resultado =mysqli_query($conexion,$query);

if($resultado){
    echo "datos insertados";
}else{
    echo "datos error";
}
mysqli_close($conexion);

?>