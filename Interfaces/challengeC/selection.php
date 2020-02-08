<?php require 'conn.php' ;

/*$sql2 =("SELECT e1.id as i1, e2.id as i2, e1.idea1 as id1, e1.idea2 as id2, e2.idea1 as id3, e2.idea2 as id4 FROM relationships e1 , relationships e2 where 
((e1.idea1 = e2.idea2 and e1.idea2 = e2.idea1) or (e1.idea1 = e2.idea1 and e1.idea2 = e2.idea2)) and not(e1.id = e2.id)");
foreach($conn->query($sql2) as $row) {
		echo "{$row['i1']};{$row['id1']};{$row['id2']} and {$row['i2']};{$row['id3']};{$row['id4']} <br>";
}*/
/*$sql = ("SELECT r_id , count(val) as ct from validation , relationships where validation.r_id = relationships.id and  val = 'true' and (relationship = 'disjoint')group by r_id ;") ; 

 foreach($conn->query($sql) as $row) {

                  if ($row["ct"] >= 2) {
                  	echo " {$row['ct']} 	; {$row['r_id']}		";
                    echo "true <br>";
                  }
                  else{
                  	echo " {$row['ct']} 	; {$row['r_id']}		";
                  echo"false <br>";
                  }

	}*/

$sql = ("SELECT rel_id , count(val) as ct from val , rel where val.rel_id = rel.id and  val = 'false' and (relationship = 'similar') ; ") ; 

 foreach($conn->query($sql) as $row) {echo "false similar : {$row['ct']} <br>";}

$sql = ("SELECT rel_id , count(val) as ct from val , rel where val.rel_id = rel.id and  val = 'true' and (relationship = 'similar') ; ") ; 

 foreach($conn->query($sql) as $row) {echo "true similar : {$row['ct']} <br>";}


$sql = ("select count(val) as ct from val where val = 'true' ;") ; 

 foreach($conn->query($sql) as $row) {echo "all true relationships : {$row['ct']}	<br>";}

 $sql = ("select count(val) as ct from val where val = 'false' ;") ; 

 foreach($conn->query($sql) as $row) {echo "all false relationships : {$row['ct']} <br>";}

 $sql = ("SELECT rel_id , count(val) as ct from val , rel where val.rel_id = rel.id and  val = 'true' and (relationship = 'disjoint') ; ") ; 

 foreach($conn->query($sql) as $row) {echo "true disjoint : {$row['ct']} <br>";}
 
  $sql = ("SELECT rel_id , count(val) as ct from val , rel where val.rel_id = rel.id and  val = 'false' and (relationship = 'disjoint') ; ") ; 

 foreach($conn->query($sql) as $row) {echo "false disjoint : {$row['ct']} <br>";}


 $sql = ("SELECT rel_id , count(val) as ct from val , rel where val.rel_id = rel.id and  val = 'true' and not (relationship = 'disjoint') ; ") ; 

 foreach($conn->query($sql) as $row) {echo "true non disjoint : {$row['ct']} <br>";}

 $sql = ("SELECT rel_id , count(val) as ct from val , rel where val.rel_id = rel.id and  val = 'false' and not (relationship = 'disjoint') ; ") ; 

 foreach($conn->query($sql) as $row) {echo "false non disjoint : {$row['ct']} <br>";}


 $sql = ("SELECT rel_id ,idea1, idea2, relationship  from val , rel where val.rel_id = rel.id and  val = 'false' and not (relationship = 'disjoint') ; ") ; 

 foreach($conn->query($sql) as $row) {echo "false non disjoint : {$row['idea1']};;;{$row['idea2']};;;{$row['relationship']} <br>";}