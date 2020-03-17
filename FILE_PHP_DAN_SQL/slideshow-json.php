<?php

include ("config.php");

$sql = "SELECT * FROM slideshow";
$result = array();
$query = mysqli_query($db, $sql);
 
while($row = mysqli_fetch_array($query)){
    array_push($result, array('judul_slideshow' => $row['judul_slideshow'],
    'url_slideshow' => $row['url_slideshow']
));
}
echo json_encode(array("result" => $result));

?>