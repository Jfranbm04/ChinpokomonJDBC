<?php 
include 'conexion.php';

$codigo =$_POST['codigo'];
$result= array();
$result['chinpokomon_fotos'] =array();
$query ="SELECT * FROM chinpokomon_fotos WHERE codigo LIKE '$codigo' ";
$response = mysqli_query($conexion,$query);
while($row = mysqli_fetch_array($response))
{
    $index['codigo'] =$row['0'];
    $index['imagen'] =$row['1'];
    array_push($result['chinpokomon_fotos'], $index);
}
$result["exito"]="1";
echo json_encode($result);

?>