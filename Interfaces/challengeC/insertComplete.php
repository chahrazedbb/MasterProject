<?php
require 'conn.php';

$member_id = $_POST['member_id'];
$Complete = $_POST['Complete'];
$sql = ("UPDATE member SET Complete = '$Complete' where id = '$member_id' ; ");
$result = $conn->exec($sql);    
if($result){
    header('location:logout.php');
}