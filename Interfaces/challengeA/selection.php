<?php require 'conn.php' ;

$sql =("SELECT what_about, how_it_works, when_it_works FROM idea ORDER BY id DESC ");

echo "****************************what about****************************************";
echo "<br/>";

foreach($conn->query($sql) as $row) { 
	echo  "\"{$row['what_about']}\",<br/>" ;
}

	echo "****************************when it works****************************************";
	echo "<br/>";

foreach($conn->query($sql) as $row) { 
	echo  "\"{$row['when_it_works']}\",<br/> " ;
}

	echo "****************************how it works****************************************";
	echo "<br/>";

foreach($conn->query($sql) as $row) { 
	echo  "\"{$row['how_it_works']}\",<br/> " ;
}
