<?php

include("config.php");

	$isi_tagline = $_POST['isi_tagline'];
	
	$sql = "UPDATE tagline SET isi_tagline='$isi_tagline' WHERE id_tagline=2";
	$query = mysqli_query($db, $sql);

	if($query){
		echo "Update Successful";
	}else{
		die("Gagal menyimpan perubahan");
	}
?>