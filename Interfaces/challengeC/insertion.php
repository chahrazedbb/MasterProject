<?php
require 'conn.php';
        $r_id = $_POST['r_id'];
        $V = $_POST['V'];
        $sql1 = "INSERT INTO validation (r_id, val) VALUES ('$r_id', '$V')";
        $result = $conn->exec($sql1);
if($result){
    header('location:challenge.php');
}

