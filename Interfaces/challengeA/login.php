<?php

session_start();

require 'password.php';

require 'conn.php';
if(isset($_SESSION['user_id']) || isset($_SESSION['logged_in'])){

    header('Location: challenge.php');
    exit;
}

if(isset($_POST['login'])){


    $email = !empty($_POST['email']) ? trim($_POST['email']) : null;
    $passwordAttempt = !empty($_POST['password']) ? trim($_POST['password']) : null;


    $sql = "SELECT id, email, password, username FROM member WHERE email = :email";

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

            header('Location: challenge.php');
            exit;

        } else{
            echo"<script language=\"javascript\">" ;
            echo"alert('Incorrect email / password combination!')";
            echo"</script>";
        }
    }
}
elseif  (isset($_POST['register'])){


    $username = !empty($_POST['username']) ? trim($_POST['username']) : null;
    $email = !empty($_POST['email']) ? trim($_POST['email']) : null;
    $pass = !empty($_POST['password']) ? trim($_POST['password']) : null;

    $sql = "SELECT COUNT(email) AS num FROM member WHERE email = :email";
    $stmt = $conn->prepare($sql);

    $stmt->bindValue(':email', $email);

    $stmt->execute();
    $row = $stmt->fetch(PDO::FETCH_ASSOC);

    if($row['num'] > 0){

        echo"<script language=\"javascript\">" ;
        echo"alert('This email already exists!')";
        echo"</script>";

    }else{
        $passwordHash = password_hash($pass, PASSWORD_BCRYPT, array("cost" => 12));

        $sql = "INSERT INTO member (username,email, password) VALUES (:username, :email, :password)";
        $stmt = $conn->prepare($sql);

        $stmt->bindValue(':username', $username);
        $stmt->bindValue(':email', $email);
        $stmt->bindValue(':password', $passwordHash);

        $result = $stmt->execute();

        if($result){
            echo"<script language=\"javascript\">" ;
            echo"alert('Thank you for registering.')";
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
        #form-switch {
            display:none
        }
        #register-form {
            display:none
        }
        #form-switch:checked~#register-form {
            display:block
        }
        #form-switch:checked~#login-form {
            display:none
        }
        label:hover{
            color: rgb(0, 189, 197); ;
        }
    </style>
</head>
<body>
    <div class="header" id="myHeader">
        <h1>Challenge</h1>
    </div>

    <div class="content">
    <p>
        In this Task we will collect ideas about a urban security, in order to (1) analyse the creativity of ideas (how novel and useful) and (2) finding relationships between ideas generated.
      </p>
      <h3>How long will the Task take? </h3>
      <p>
        This Task will take approximately 10 minutes Brainstorming session
      </p>


      <h3>Who are we?</h3>
      <p>
        At the <a href="https://www.fu-berlin.de/en/"></a> University of Djilali Liebes, Algeria, we are currently developing a system for creativity support and idea management. This system uses Natural Language Processing, Semantic Technologies and Matching to support users in brainstorming sessions as well as managing ideas.
      </p>


      <h3>What happens to my ideas?</h3>
        <p>
        After the task is complete, your ideas will be saved and analysed using statistical methods. In order to provide the idea corpus to other researchers, we will publish the anonymized ideas.
        <br/>
        Please accept this task only if you are absolutely sure that you can commit your full attention for the next 10 minutes.
        <br/>
         By accepting the HIT you agree to this usage, so let start!!!
        </p>
    </div>


<input type='checkbox' id='form-switch'>
<form id='login-form' action="" method="post">
    <input type="email" name="email" placeholder="Email" required>
    <input type="password" name="password" placeholder="Password" required>
    <button type='submit' name="login" >Login</button>
    <label for='form-switch'>Not a Member ? Register Now..</label>
</form>
<form id='register-form' action="" method='post'>
    <input type="text" name="username" placeholder="usname" required>
    <input type="email" name="email" placeholder="Email" required>
    <input type="password" name="password" id="password" placeholder="Password" required >
    <input type="password" name="repassword" id="repassword" placeholder="Re Password" required >
    <span id='message'></span>
    <button type='submit' name="register" id="register">Register</button>
    <label for='form-switch'>Already Member ? Sign In Now..</label>
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