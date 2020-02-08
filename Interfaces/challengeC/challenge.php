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

    $sql =("SELECT * from  rel; ");

    if (isset($_POST['true'])) {
        $r_id = $_POST['r_id'];
        $Vsession = $_POST['Vsession'];
      
        $sql1 = ("INSERT INTO val (rel_id, val) VALUES ('$r_id', 'true')");
      
        $result = $conn->exec($sql1); 
      
      }
    if (isset($_POST['false'])) {
        $r_id = $_POST['r_id'];
        $Vsession = $_POST['Vsession'];
        $sql1 = ("INSERT INTO val (rel_id, val) VALUES ('$r_id','false')");
        $result = $conn->exec($sql1); 
      }
      if (isset($_POST['sub'])) {
        $Complete = $_POST['Complete'];
        $sql = ("UPDATE member SET Complete = '$Complete' where id = '$idm' ; ");
        $result = $conn->exec($sql);    
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
            padding: 10px 15px;
            border-radius: 5px;
            cursor: pointer;
            float: left;
            font-size: 15px;
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

            input[type=text] {
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
                width: 100%
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
  height: 1000px;
}


/*** Content ***/

.slider-container
{
  margin: 0 auto;
  padding: 0;
  width: 700px;
  min-height:1000px;
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
                <h1>Challenge</h1>
                <p>Welcome <?php echo $user ; ?></p>
            </div>

    </div>
    <div id="slider">
        <div class="content">
                <p>
                   <b> Please validate if the relationship between ideas is true or false </b>
                </p>
        </div>
      <?php foreach($conn->query($sql) as $row) { ?>
        <div class="slider-container">
          <div class="idea">
            <b style="text-decoration: underline;">Idea 1</b>
              <p>
                <?php echo "{$row['idea1']}"; ?>
              </p>
          </div>
              <h4>Is <?php echo "{$row['relationship']} ";?> to</h4>
          <div class="idea"> 
            <b style="text-decoration: underline;">Idea 2</b>
              <p>
                <?php echo "{$row['idea2']} "; ?>
              </p>
          </div>  
            <form action="" method="post">
            <input type="hidden" name="r_id" id="r_id" value="<?php echo $row['id'] ?>">
            <input type="hidden" name="index" id="index">
            <input type="hidden" name="member_id" id="member_id" value="<?php echo $idm ?>">
            <input type="hidden" name="Vsession" id="Vsession" value="<?php echo $row['Vsession'] ?>">
            <input type="hidden" name="Complete2" id="Complete2" value="<?php echo $row['Complete'] ?>">
            <input type="submit" name="true" onclick="trueV(1)" value="TRUE" style="background-color: #008CBA ;color: white; border: 2px solid #008CBA;">
            <input type="submit" name="false" onclick="falseV(1)" value="FALSE" style="background-color: white; color: #008CBA; border: 2px solid #008CBA;">
            </form>
        </div>
      <?php } ?>
        <form id="myform" method="post" action="insertComplete.php">
          <input type="hidden" name="member_id" id="member_id" value="<?php echo $idm ?>">
          <input type="hidden" name="Complete" id="Complete" value="1">
        </form>
    </div>
</div>
<script type='text/javascript' src='https://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js'></script>
<script>
var Complete = document.getElementById("Complete2").value;


  var slideIndex = Number(getSavedValue("index"));
  showDivs(slideIndex);


  function showDivs(n) {
    var i;
    var x = document.getElementsByClassName("slider-container");

    if (n == x.length) {
    document.getElementById("Complete").value = 1 ;
    }

    if (n > x.length) {
    slideIndex = 1; 
    alert("You have validate all relationships"); 
    document.getElementById("index").value = 1;
    localStorage.setItem('index', 1);

   document.getElementById("myform").submit();
    return false ;
  }
    if (n < 1) {slideIndex = x.length}
    for (i = 0; i < x.length; i++) {
      x[i].style.display = "none";  
    }
    x[slideIndex-1].style.display = "block";  
  }

  function trueV(n){
    document.getElementById("index").value =  slideIndex + n;
    localStorage.setItem('index', slideIndex + n);

    alert(document.getElementById("index").value + "\n" +
      document.getElementById("V").value);
    }

  function falseV(n){
    document.getElementById("index").value =  slideIndex + n;
    localStorage.setItem('index', slideIndex + n);
    }


  function getSavedValue  (v){
      if (localStorage.getItem(v) == null) {
          return "1";
      }
      return localStorage.getItem(v);

}
</script>  
</body>
</html>