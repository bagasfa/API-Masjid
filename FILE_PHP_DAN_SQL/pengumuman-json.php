<?php

include ("config.php");

$sql = "SELECT * FROM pengumuman_masjid";
$result = array();
$query = mysqli_query($db, $sql);
 
while($row = mysqli_fetch_array($query)){
    array_push($result, array('judul_pengumuman' => $row['judul_pengumuman'],
    'isi_pengumuman' => $row['isi_pengumuman'],
    'aktif' => $row['aktif']
));
}
echo json_encode(array("result" => $result));

?>