<?php

include("config.php");


$sql = "SELECT * FROM jadwal_sholat";
$result = array();
$query = mysqli_query($db, $sql);
 
while($row = mysqli_fetch_array($query)){
    array_push($result, array('shubuh' => $row['shubuh'],
    'dhuhur' => $row['dhuhur'],
    'ashar' => $row['ashar'],
    'maghrib' => $row['maghrib'],
    'isha' => $row['isha'],
    'dhuha' => $row['dhuha']
));
}
echo json_encode(array("result" => $result));

echo "<br>";

$sql = "SELECT * FROM identitas_masjid";
$result = array();
$query = mysqli_query($db, $sql);
 
while($row = mysqli_fetch_array($query)){
    array_push($result, array('nama_masjid' => $row['nama_masjid'],
    'alamat_masjid' => $row['alamat_masjid']
));
}
echo json_encode(array("result" => $result));

echo "<br>";

$sql = "SELECT * FROM marquee";
$result = array();
$query = mysqli_query($db, $sql);
 
while($row = mysqli_fetch_array($query)){
    array_push($result, array('isi_marquee' => $row['isi_marquee']
));
}
echo json_encode(array("result" => $result));

echo "<br>";

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

echo "<br>";

$sql = "SELECT * FROM slideshow";
$result = array();
$query = mysqli_query($db, $sql);
 
while($row = mysqli_fetch_array($query)){
    array_push($result, array('judul_slideshow' => $row['judul_slideshow'],
    'url_slideshow' => $row['url_slideshow']
));
}
echo json_encode(array("result" => $result));

echo "<br>";

$sql = "SELECT * FROM tagline";
$result = array();
$query = mysqli_query($db, $sql);
 
while($row = mysqli_fetch_array($query)){
    array_push($result, array('isi_tagline' => $row['isi_tagline']
));
}
echo json_encode(array("result" => $result));


?>



