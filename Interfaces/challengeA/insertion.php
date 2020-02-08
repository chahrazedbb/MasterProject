<?php
require 'conn.php';


$idm = $_POST['idm'];
$what = $_POST['what'];
$how = $_POST['how'];
$when = $_POST['when'];
$time = $_POST["time"];
$session = $_POST["session"];
$idea_date = $_POST["idea_date"];

$sql = "INSERT INTO idea (what_about, how_it_works, when_it_works,member_id,time,session,idea_date)
    VALUES ('$what', '$how', '$when','$idm','$time','$session','$idea_date')";

$result = $conn->exec($sql);

if($result){
    header('location:challenge.php');
}

