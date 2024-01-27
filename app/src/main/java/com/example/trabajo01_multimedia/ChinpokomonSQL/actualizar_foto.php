<?php 

include 'conexion.php';

$idProducto =$_POST['codigo'];
$foto=$_POST['imagen'];


$query ="UPDATE chinpokomon_fotos SET imagen ='$imagen' WHERE codigo LIKE '$codigo'";

$resultado =mysqli_query($conexion,$query);

if($resultado){
    echo "datos actualizados";
}else{
    echo "error en actualizacion";
}


mysqli_close($conexion);

?>