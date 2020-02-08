<?php

  session_start();

    if(!isset($_SESSION['user_id']) || !isset($_SESSION['logged_in'])){

        header('Location: login.php');
        exit;
    }

    if (isset($_POST['Logout'])) {

        session_destroy();
        header('location: login.php');
    }

    $idm = $_SESSION['user_id'] ;
    $user = $_SESSION['user_name'];

    require 'conn.php' ;

    $sql =("SELECT what_about, how_it_works, when_it_works, id  FROM idea ; ");


 if (isset($_POST['insertion_ideas'])){
  $what = $_POST['what'];
  $how = $_POST['how'];
  $when = $_POST['when'];
  $sql20 = "INSERT INTO idea (what_about, how_it_works, when_it_works)
    VALUES ('$what', '$how', '$when')";
    $result = $conn->exec($sql20);

 }

 if (isset($_POST['insertion_relationship'])){
  $idea1 = $_POST['idea1'];
  $idea2= $_POST['idea2'];
  $relationship = $_POST['relationship'];
  $Vsession = $_POST['Vsession'];
  $sql30 = "INSERT INTO relationships (idea1,idea2,relationship,Vsession)
    VALUES ('$idea1', '$idea2', '$relationship' , '$Vsession')" ;
    $result = $conn->exec($sql30);

  }

   if (isset($_POST['delete'])) {
    $ideaid = $_POST['ideaid'];
        $sql2 = ("DELETE FROM relationships  WHERE '$ideaid'=id");
        $result = $conn->exec($sql2);
        if($result){
            header('location:challenge-admin.php');
        }
    }

?>
<html>
<head>
  <title></title>
<meta name="viewport" content="width=device-width, initial-scale=1">
  <style type="text/css">
 

            html, body, {
            }

            body {
              font-family: Arial, Helvetica, sans-serif;
            }
            h1{
                text-decoration: underline;
                font-family: "Times New Roman", Times, serif;
            }

            label {
                padding: 12px 12px 12px 0;
                display: inline-block;
            }

            input[type=submit] {
            padding: 12px 20px;
                border-radius: 4px;
                cursor: pointer;
                background-color: #008CBA;
                                color: white;  
                border: none;
       }

            input[type=reset] {
                padding: 12px 20px;
                border-radius: 4px;
                cursor: pointer;
                background-color: yellow;
                border: none;
            }
            a {
        text-decoration: none;
        padding: 12px 20px;
                border-radius: 4px;
                cursor: pointer;
                border: none;
                background-color: #008CBA; 
                color: white;  
              font-size: 13px;
      }
            .container {
                border-radius: 10px;
                background-color: white;
                padding: 5px;
                margin: 2%;
                padding-top: 2px;
            }

            .col-25 {
                float: left;
                width: 25%;
                margin-top: 6px;
            }

            .col-75 {
                float: left;
                width: 75%;
                margin-top: 6px;
            }

            /* Clear floats after the columns */
            .row:after {
                content: "";
                display: table;
                clear: both;
            }

            select, option, input[type=text] {
                width: 70%;
                padding: 12px;
                border: 1px solid #ccc;
                border-radius: 4px;
                resize: vertical;
            }
            textarea {
               width: 90%;
              height: 50px;
              padding: 12px 20px;
              box-sizing: border-box;
              border: 2px solid #ccc;
              border-radius: 4px;
              font-size: 16px;
              resize: none;
            }

            .idea {
                border-radius: 2px;
                background-color:   #87CEFA;
                padding: 20px;
                margin: 2%;
                width: 70%
            }

            .idea2 {
                border-radius: 2px;
                background-color:   #87CEFA;
                padding: 10px;
                margin: 2%;
                width: 90%;
            }
            .footer{
              float: right;
              width: 100%;
              text-align: center;
              bottom: : 0 ;
            }

            button {
            padding: 5px 10px;
            border-radius: 2px;
            cursor: pointer;
            float: right;
            background-color: white;
            color: #008CBA;
            border: 2px solid #008CBA;
        }
            .delete {

            border-radius: 2px;
            cursor: pointer;
            background-color: white;
            color: #008CBA;
            border: 2px solid #008CBA;
            position: absolute;
            right: 300px;
            }
a
{
  text-decoration: none;
  color: #111b47;
}

a:hover
{
  border-bottom: 1px dashed #ED971F;
  color: #ED971F;
}

/**** slider ****/

#slider
{
  height: 200px;
}

#slider
{
  margin: auto;
  overflow: hidden;
  padding: 20px;
  margin-top: 50px;
  position: relative;
  width: 800px;
  height: 400px;
}


/*** Content ***/

.slider-container
{
  margin: 0 auto;
  padding: 0;
  width: 700px;
  min-height: 800px;
  display: none;
  -webkit-animation: slide-animation ;
}

.slider-container h4
{
  color: #0A7FAD;
}

.slider-container  p
{
  margin: 10px 25px;
  font-weight: semi-bold;
  line-height: 150%;
  text-align: justify;
}


 </style>
</head>
<body>
<div>
   <div style="min-width: 1200px; margin: 0 auto; max-height: 1080px;">
            <div class="header" id="myHeader">
                <form method="post" action="" id="logout">
                    <button type='submit' name="Logout" onclick="resetValues()">Logout</button>
                </form>
                <form method="post" id="All" action="">
                  <button type="submit" name="All">All idea</button>
                </form>
                <form method="post" id="showValidate" action="">
                  <button type="submit" name="showValidate">Validated Ideas</button>
                </form>
                <h1>Challenge</h1>
                <p>Welcome <?php echo $user ; ?></p>
            </div>

    </div>
      <div>
        
      </div>
        <h3>insert new ideas</h3>
        <form method="post" action="">
                            <div class="row">
                                <div class="col-25">
                                    <label for="fname">What is it about:</label>
                                </div>
                                <div class="col-75">
                                    <textarea name="what" id="what" required></textarea>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-25">
                                    <label for="lname">How it works:</label>
                                </div>
                                <div class="col-75">
                                    <textarea name="how" id="how"  required></textarea>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-25">
                                    <label for="country">When it works:</label>
                                </div>
                                <div class="col-75">
                                    <textarea name="when" id="when"  required></textarea>
                                </div>
                            </div>
                            <div class="row">

                                <input type="reset" value="Reset">
                               
                                <input type="submit" value="submit" name ="insertion_ideas">

                            </div>
        </form>
       <div>
        <h3>insert new relationships</h3>
          <form method="post" action="">
            <div class="row">
              <div class="col-25">
                  <label for="country">Idea 1:</label>
                </div>
                <div class="col-75">
            <select name="idea1">
              <?php foreach($conn->query($sql) as $row) { ?>
                <option value="<?php echo $row['id'] ?>"> <?php echo "{$row['what_about']}, {$row['how_it_works']}, {$row['when_it_works']} "; ?></option>
              <?php } ?>
            </select>
          </div>
            </div>
            <div class="row">
              <div class="col-25">
                                <label for="country">Idea 2:</label>
                                </div>
               <div class="col-75">
            <select name="idea2">
              <?php foreach($conn->query($sql) as $row) { ?>
                <option value="<?php echo $row['id'] ?>"> <?php echo "{$row['what_about']}, {$row['how_it_works']}, {$row['when_it_works']} "; ?></option>
              <?php } ?>
            </select>
          </div>
          </div>
            <div class="row">
              <div class="col-25">
                  <label for="country">Relationship :</label>
                </div>
               <div class="col-75">
            <select name="relationship">
              <option value="disjoint">disjoint</option>
              <option value="alternative solution">alternative solution</option>
              <option value="similar">similar</option>
              <option value="duplicate">duplicate</option>
              <option value="generalize/specialize">generalize/specialize</option>
              <option value="generalizes">generalizes</option>
            </select>
          </div>
        </div>
          <div class="row">
              <div class="col-25">
                <label for="country">session number :</label>
              </div>
               <div class="col-75">
              <input type="text" id="Vsession" name="Vsession">
           </div>
         </div>
           <div class="row">

                                <input type="reset" value="Reset">
                                <input type="submit" value="submit" name="insertion_relationship">

                            </div>
          </form>
      </div>
      <div>
        <h3>Validated relationships</h3>
        <?php
           if (isset($_POST['showValidate'])){
               $sql8= ("SELECT i1.what_about as i1what, i1.how_it_works as i1how, i1.when_it_works as i1when, i2.what_about as i2what , i2.how_it_works as i2how, i2.when_it_works as i2when, relationship , relationships.id , r_id , val
                FROM idea i1 ,idea i2 , relationships , validation
                WHERE   i1.id = idea1 and i2.id = idea2 
                and validation.r_id = relationships.id order by r_id; ");
               
                foreach($conn->query($sql8) as $row) { 
       echo "{$row['r_id']};;;{$row['i1what']}, {$row['i1how']}, {$row['i1when']};;;{$row['relationship']};;;{$row['i2what']}, {$row['i2how']}, {$row['i2when']} ;;; {$row['val']}";
       echo "<br>";
                }
             }
         ?>
      </div>
            <div>
        <h3>All ideas</h3>
         <?php
           if (isset($_POST['All'])){
               $sql0 =("SELECT i1.what_about as i1what, i1.how_it_works as i1how, i1.when_it_works as i1when, i2.what_about as i2what , i2.how_it_works as i2how, i2.when_it_works as i2when, relationship , relationships.id as rel , Vsession
                FROM idea i1 ,idea i2 , relationships 
                WHERE   i1.id = idea1 and i2.id = idea2  order by rel ; ");

                foreach($conn->query($sql0) as $row) { 
                   echo "<form method='post' action=''> ";
                echo   " <input type='hidden' name='ideaid' value=' {$row['rel']}'> ";
                echo   " <input type='submit' value='Remove' name='delete' class='delete' >";
                echo    "</form>" ;
                  echo "<div class='idea'>";
                 echo "{$row['i1what']}, {$row['i1how']}, {$row['i1when']} "; 
                  echo  "<h3> {$row['relationship']} </h3>"; 
                 echo "{$row['i2what']}, {$row['i2how']}, {$row['i2when']} ";
                 echo "<h3>Vsession {$row['Vsession']}   </h3>";
                 echo "</div>";
                }
             }
         ?>
      </div>
       <div>
</div>
</body>
</html>