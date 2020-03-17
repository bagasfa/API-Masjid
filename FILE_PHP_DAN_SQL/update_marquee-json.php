<?php

include("config.php");

	$isi_marquee = $_POST['isi_marquee'];

	$sql = "UPDATE marquee SET isi_marquee='$isi_marquee' WHERE id_marquee=1";
	$query = mysqli_query($db, $sql);

	if($query){
		echo "Update Successful";
	}else{
		die("Gagal menyimpan perubahan");
	}
?>