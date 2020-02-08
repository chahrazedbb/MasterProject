<?php
    session_start();

    if(!isset($_SESSION['user_id']) || !isset($_SESSION['logged_in'])){

        header('Location: login-admin.php');
        exit;
    }

    if (isset($_POST['Logout'])) {

        session_destroy();
        header('location: login-admin.php');
    }

    $idm = $_SESSION['user_id'] ;
    $user = $_SESSION['user_name'];


    require 'conn.php' ;

    $sql =("SELECT id , username , Vsession FROM member  ORDER BY id DESC ");
   
    $sql3 = ("SELECT COUNT(id) AS members   FROM member");

    $sql4 = ("SELECT COUNT(id) AS ideas   FROM idea");


    if (isset($_POST['addSession'])) {
        $id_member = $_POST['id_member'];
        $session = $_POST['session'];
        $sql2 = ("UPDATE member SET  Vsession='$session' where id = '$id_member' ;");
        $result = $conn->exec($sql2);
        if($result){
            header('location:challenge-admin.php');
        }
    }
?>

<html>
    <head>
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
            .delete {

            border-radius: 2px;
            cursor: pointer;
            background-color: white;
            color: #008CBA;
            border: 2px solid #008CBA;
            position: absolute;
            right: 300px;
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
            margin: 0.5%;
            }

            .left {
                float:left;
                width:55%;
                background-color: #E6E6FA;
                padding: 10px;
                box-shadow: 5px 10px 8px #888888;
                margin: 0.5%;
                height: auto;
            }

            .right {
                float:left;
                width:40%;
                height:600px
                height: auto;
            }

            .idea {
                border-radius: 2px;
                background-color: 	#87CEFA;
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

        table.d {
          table-layout: fixed;
          width: 100%;  
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
        <div style="margin-left : 2% ;">
             <p><b>Total number of members : </b><?php $row2 = $conn->query($sql3)->fetch(); echo $row2['members']; ?>
                
            </p>
            <p><b>Total number of new ideas : </b><?php $row4 = $conn->query($sql4)->fetch(); echo $row4['ideas']; ?>
            </p>
            <p><b>Members</b></p>
           
            <?php foreach($conn->query($sql) as $row) { ?>
                   <b> <?php echo "{$row['username']}"; ?> </b>
                   <form action="" method="post">
                       <input type="hidden" name="id_member" value="<?php echo $row['id']; ?>">
                       <input type="text" name="session" value="<?php echo $row['Vsession']; ?>">
                       <input type="submit" value="Add Session" name="addSession" >
                   </form>
                </div>
            <?php } ?>
        </div>
</div>

</body>
</html>