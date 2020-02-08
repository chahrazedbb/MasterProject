<?php

session_start();

require 'password.php';

require 'conn.php';

if(isset($_SESSION['user_id']) || isset($_SESSION['logged_in'])){

    header('Location: challenge-admin.php');
    exit;
}

if(isset($_POST['login'])){


    $email = !empty($_POST['email']) ? trim($_POST['email']) : null;
    $passwordAttempt = !empty($_POST['password']) ? trim($_POST['password']) : null;


    $sql = "SELECT id, email, password, username FROM admin WHERE email = :email";

    $stmt = $conn->prepare($sql);


    $stmt->bindValue(':email', $email);


    $stmt->execute();


    $user = $stmt->fetch(PDO::FETCH_ASSOC);

    if($user === false){

        echo"<script language=\"javascript\">" ;
        echo"alert('Incorrect email / password combination!')";
        echo"</script>";
    } else{

        $validPassword = password_verify($passwordAttempt, $user['password']);

        if($validPassword){

            $_SESSION['user_id'] = $user['id'];
            $_SESSION['user_name'] = $user['username'];
            $_SESSION['logged_in'] = time();

            header('Location: challenge-admin.php');
            exit;

        } else{
            echo"<script language=\"javascript\">" ;
            echo"alert('Incorrect email / password combination!')";
            echo"</script>";
        }
    }
}

?>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title></title>
    <style type="text/css">
body {
  margin: 0;
  font-family: Arial, Helvetica, sans-serif;
}

.header {
  padding: 10px 16px;
  background:  rgb(0, 189, 197);
  color: #f1f1f1;
}

.content {
  padding: 16px;
}


h1{
    text-decoration: underline;
    font-family: "Times New Roman", Times, serif;
}

h3{
       font-family: "Times New Roman", Times, serif;
       font-size: 24px; 
}

p {
    font-family: "Times New Roman", Times;
    padding-left: 30px;
    font-size: 17px;
}
        form {
            background-color: white;
            box-sizing: border-box;
            box-shadow: 2px 2px 5px 1px rgba(0, 0, 0, 0.2);
            width: 600px;
            margin: 100px auto 0;
            padding-bottom: 30px;
            padding-top: 20px;
        }
        input {
            margin: 40px 25px;
            width: 500px;
            display: block;
            border: none;
            padding: 10px 0;
            border-bottom: solid 1px ;
            font-size: 20px;
        }
        button {
            border: none;
            background-color: #008CBA;
            cursor: pointer;
            border-radius: 3px;
            padding: 6px;
            width: 100px;
            height: 40px;
            color: white;
            margin-left: 25px;
            box-shadow: 0 3px 6px 0 rgba(0, 0, 0, 0.2);
        }
        label {
            cursor:pointer
        }
    </style>
</head>
<body>
    <div class="header" id="myHeader">
        <h1>Challenge</h1>
    </div>

<form id='login-form' action="" method="post">
    <input type="email" name="email" placeholder="Email" required>
    <input type="password" name="password" placeholder="Password" required>
    <button type='submit' name="login" >Login</button>
</form>
<br/><br/>
<br/><br/>

<script type="text/javascript">
    var password = document.getElementById("password")
        , repassword = document.getElementById("repassword");

    function validatePassword(){
        if(password.value != repassword.value) {
            repassword.setCustomValidity("Passwords Don't Match");
        } else {
            repassword.setCustomValidity('');
        }
    }

    password.onchange = validatePassword;
    repassword.onkeyup = validatePassword;
</script>
<script>
window.onscroll = function() {myFunction()};

var header = document.getElementById("myHeader");
var sticky = header.offsetTop;

function myFunction() {
  if (window.pageYOffset > sticky) {
    header.classList.add("sticky");
  } else {
    header.classList.remove("sticky");
  }
}
</script>
</body>
</html>