<?php 
include 'conexion.php';


$result= array();
$result['listaChinpokomon'] =array();
$query ="SELECT * FROM chinpokomon";
$responce = mysqli_query($conexion,$query);

while($row = mysqli_fetch_array($responce))
{
    $index['codigo'] =$row['0'];
    $index['nombre'] =$row['1'];
    $index['nivel'] =$row['2'];
    $index['tipo'] =$row['3'];
    $index['movimiento'] =$row['4'];
    

    array_push($result['listaChinpokomon'], $index);

}
$result["exito"]="1";
echo json_encode($result);

?>