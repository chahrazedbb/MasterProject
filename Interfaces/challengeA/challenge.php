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

    $sql =("SELECT what_about, how_it_works, when_it_works FROM idea WHERE '$idm' = member_id ORDER BY id DESC ");


    $sq =("SELECT * FROM idea WHERE '$idm' = member_id  ORDER BY session DESC LIMIT 1");


    foreach($conn->query($sq) as $row) {
        $ses = $row['session'] ;
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


.btn-container {
  display: flex;
  flex-wrap: wrap;
  justify-content: space-around;
}
.btn {
  background-color: white; color: #008CBA; border: 2px solid #008CBA;
   padding: 12px 20px;
                border-radius: 4px;
                cursor: pointer;
            
}
.btn--shockwave.is-active {
  animation: shockwaveJump 10s ease-out infinite;
}
.btn--shockwave.is-active:after {
  content: '';
  position: absolute;
  top: 0;
  left: 0;
  bottom: 0;
  right: 0;
  border-radius: 50%;
  animation: shockwave 1s 0.65s ease-out infinite;
}
.btn--shockwave.is-active:before {
  content: '';
  position: absolute;
  top: 0;
  left: 0;
  bottom: 0;
  right: 0;
  border-radius: 50%;
  animation: shockwave 1s 0.5s ease-out infinite;
}
@keyframes shockwaveJump {
  0% {
    transform: scale(1);
  }
  40% {
    transform: scale(1.08);
  }
  50% {
    transform: scale(0.98);
  }
  55% {
    transform: scale(1.02);
  }
  60% {
    transform: scale(0.98);
  }
  100% {
    transform: scale(1);
  }
}
@keyframes shockwave {
  0% {
    transform: scale(1);
    box-shadow: 0 0 2px rgba(0, 0, 0, 0.15), inset 0 0 1px rgba(0, 0, 0, 0.15);
  }
  95% {
    box-shadow: 0 0 50px rgba(0, 0, 0, 0), inset 0 0 30px rgba(0, 0, 0, 0);
  }
  100% {
    transform: scale(2.25);
  }
}
.btn--jump.is-active {
  animation: 0.4s jump ease infinite alternate;
}
@keyframes jump {
  0% {
    transform: scale(1);
    box-shadow: 0 1px 2px rgba(0, 0, 0, .15);
  }
  100% {
    transform: scale(1.05);
    box-shadow: 0 4px 20px rgba(0, 0, 0, .1);
  }
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
            <div class="content">
                <p>
                    Your task is to come up with many ideas as you can to address the problem below. Be as specific as possible in your responses.
                </p>
                <p style="font-size: 20px;">We are searching for innovative (technical) solution for the security of city building. In the first step think of possible dangerous events, which might occur (e.g. fire). Then brainstorm innovative solutions, how people in the bulding could be protected from such a danger or rescued from the building.</p>
            </div>
        </div>
        <div style="margin-left : 2% ;">
            <p id="clockdiv">
                Time left: <span class="minutes"></span>:<span class="seconds"></span>
            </p>
        </div>
        <div  style="min-width: 1200px; margin: 0 auto; max-height: 1080px;">
                <div class="left" >
                    <h3>
                        Submit a new idea
                    </h3>
                    <div class="container" >
                        <p>
                            Please describe your ideas as follows:
                        </p>
                        <form method="post" action="insertion.php" id="myform">
                            <input type="hidden" name="idm" value="<?php echo $idm; ?>">
                            <div class="row">
                                <div class="col-25">
                                    <label for="fname">What is it about:</label>
                                </div>
                                <div class="col-75">
                                    <textarea name="what" id="what" onkeyup='saveValue(this);' required></textarea>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-25">
                                    <label for="lname">How it works:</label>
                                </div>
                                <div class="col-75">
                                    <textarea name="how" id="how" onkeyup='saveValue(this);' required></textarea>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-25">
                                    <label for="country">When it works:</label>
                                </div>
                                <div class="col-75">
                                    <textarea name="when" id="when" onkeyup='saveValue(this);' required></textarea>
                                </div>
                            </div>

                            <input type="hidden" name="time" id="time" onkeyup='saveValue(this);'>
                            <input type="hidden" name="session" id="session" value="<?php echo $ses ?>">
                            <input type="hidden" name="idea_date" id="idea_date">

                            <div class="row">

                                <input type="reset" value="Reset" onclick="resetValuesOnly()">
                                <a href="javascript: getTime()">Submit</a>

                            </div>
                         </form>

                         <div style="font-size: 13px">
                         <p>What about: describes what the idea is about</p>
                         <p>How it works: describes how the idea works</p>
                         <p>When it works: describes when the idea works</p>
                        </div>
                    </div> 
                    <hr width="600px">
                    <p>
                        Your previous ideas
                    </p>
                        <?php foreach($conn->query($sql) as $row) { ?>
                    <div class="idea">
                        <?php
                        echo "<table class='d'>
                                <tr>
                                    
                                    <td valign='top' width='30%'>
                                        <b>what is it about:</b>
                                     </td>
                                  
                                    <td valign='top' style='word-wrap: break-word'>
                                        {$row['what_about']}
                                    </td>
                                </tr>
                                </table>
                                <hr width='200px'>
                                <table class='d'>
                                <tr>
                                    
                                     <td valign='top' width='30%'>
                                     <b>how it works: </b>
                                     </td>
                                   
                                    <td valign='top' style='word-wrap: break-word'>
                                        {$row['how_it_works']}
                                    </td>
                                </tr>
                                </table>
                                <hr width='200px'>
                                <table class='d'>
                                <tr>
                                 
                                    <td valign='top' width='30%'>
                                     <b>when it works: </b>
                                     </td>
                                   
                                    <td valign='top' style='word-wrap: break-word'>
                                        {$row['when_it_works']}
                                    </td>
                                </tr>
                            </table>";
                        ?>
                    </div>
                    <?php } ?>
                </div>

                <div class="right">
                    <center>
                        <form action="" method="post">
                            <div class="btn-container">
                            <button type='submit' name="insp" class="btn btn--shockwave is-active">NEED INSPIRATION ?</button>
                       </div>
                        </form>
                        <p style="margin-left : 20px;">
                            Click the button above and you will be presented with a set of others'ideas.
                        </p>
                        <p style="margin-left : 20px;">
                            Feel free to use them as inspiration: remix them with your own ideas, expand on them, or use them in any way you'd like!
                        </p>
                        <hr width="400px">
                        <?php
                        if (isset($_POST['insp'])) {
                            $sql2 = (" SELECT what_about, how_it_works, when_it_works 
                               FROM idea 
                               ORDER BY RAND() 
                               LIMIT 3;");
                            foreach($conn->query($sql2) as $row) { ?>
                            	
                   <div class="idea2">
                                    <?php
                        echo "<table class='d'>
                                <tr>
                                    
                                    <td valign='top' width='30%'>
                                        <b>what is it about:</b>
                                     </td>
                                  
                                    <td valign='top' style='word-wrap: break-word'>
                                        {$row['what_about']}
                                    </td>
                                </tr>
                                </table>
                                <hr width='200px'>
                                <table class='d'>
                                <tr>
                                    
                                     <td valign='top' width='30%'>
                                     <b>how it works: </b>
                                     </td>
                                   
                                    <td valign='top' style='word-wrap: break-word'>
                                        {$row['how_it_works']}
                                    </td>
                                </tr>
                                </table>
                                <hr width='200px'>
                                <table class='d'>
                                <tr>
                                 
                                    <td valign='top' width='30%'>
                                     <b>when it works: </b>
                                     </td>
                                   
                                    <td valign='top' style='word-wrap: break-word'>
                                        {$row['when_it_works']}
                                    </td>
                                </tr>
                            </table>";
                        ?>
                    </div>
                        <?php } };?>
                    </center>
                </div>
        </div> 
        <div class="footer">
        
            <p style="font-size:12px;">
                P.S: if you have any issues with the system, try refreching the page: it will maintain your ideas and the timer in the same place as before.
            </p>
        </div>
</div>
<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<script type="text/javascript">

            // timer
            var timeInMinutes = 10;
            var currentTime = Date.parse(new Date());
            var deadline;
            var session;
            var timeinterval = 0 ;
            if(isNaN(document.getElementById('session').value)){ session = 0 ;}
            else{session = Number(document.getElementById('session').value);}

            if(localStorage.getItem("deadline") != 0) {
                deadline = new Date(localStorage.getItem("deadline"));
                document.getElementById('session').value = session;
            } else {
                deadline = new Date(currentTime + timeInMinutes*60*1000);
                session = session + 1 ;
                document.getElementById('session').value = session;
            }

            function getTimeRemaining(endtime){
                var t = Date.parse(endtime) - Date.parse(new Date());
                var seconds = Math.floor( (t/1000) % 60 );
                var minutes = Math.floor( (t/1000/60) % 60 );
                return {
                    'total': t,
                    'minutes': minutes,
                    'seconds': seconds
                };
            }

            function initializeClock(id,endtime){
                var clock = document.getElementById(id);
                function updateClock(){
                    var t = getTimeRemaining(endtime);
                    var minutesSpan = clock.querySelector('.minutes');
                    var secondsSpan = clock.querySelector('.seconds');
                    minutesSpan.innerHTML = t.minutes;
                    secondsSpan.innerHTML = ('0' + t.seconds).slice(-2);
                    if(t.total==0){
                        resetValues();
                        clearInterval(timeinterval);
                       // window.location.href = 'logout.php';
                                               alert("time is over !");

                        window.location.href="challenge.php";
                    }else if(t.total<0){
                        resetValues();
                        clearInterval(timeinterval);
                       // window.location.href = 'logout.php';
                        window.location.href="challenge.php";
                    }else{
                        localStorage.setItem("deadline", deadline);
                    }
                }
                updateClock();
                timeinterval = setInterval(updateClock,1000);
            }

            initializeClock('clockdiv',deadline);

            function getTime(){
                var t = Date.parse(deadline) - Date.parse(new Date());
                var seconds = Math.floor( (t/1000) % 60 );
                var minutes = Math.floor( (t/1000/60) % 60 );

                if (minutes < 10) {
                    minutes = "0" + minutes;
                }
                if (seconds < 10) {
                    seconds = "0" + seconds;
                }
                document.getElementById("time").value = "00:" + minutes + ":" + seconds ;
                var dt = new Date() ;
                document.getElementById("idea_date").value  = (dt.getFullYear())+"-"+ (("0"+(dt.getMonth()+1)).slice(-2)) +"-"+ (("0"+dt.getDate()).slice(-2))  +" "+ (("0"+dt.getHours()).slice(-2)) +":"+ (("0"+dt.getMinutes()).slice(-2)) + ":" + (("0"+dt.getSeconds()).slice(-2));  

                var xx = document.getElementById("what").value.trim();
                var yy = document.getElementById("how").value.trim();
                var zz = document.getElementById("when").value.trim();

                if(xx=="" || yy=="" || zz==""){
                    alert("all filleds must be filled out");
                    if (xx == "") {
                        document.getElementById("what").value = "";
                         localStorage.setItem('what', '');
                    } 
                    if (yy=="") {
                        document.getElementById("how").value = "";
                        localStorage.setItem('how', '');
                    }
                    if (zz=="") {
                        document.getElementById("when").value = "";
                        localStorage.setItem('when', '');
                    }
//                    return false;
                }else{

                document.getElementById("myform").submit();


                document.getElementById("what").value = "";
                document.getElementById("how").value = "";
                document.getElementById("when").value = "";

                localStorage.setItem('what', '');
                localStorage.setItem('how', '');
                localStorage.setItem('when', '');

                }
            }


            // keeping inputs values after refresh
            function saveValue(e){
                var id = e.id;
                var val = e.value;
                localStorage.setItem(id, val);  }

            function getSavedValue  (v){
                if (localStorage.getItem(v) == null) {
                    return "";
                }
                return localStorage.getItem(v);
            }

            function resetValuesOnly(){

                
                document.getElementById("what").value = "";
                document.getElementById("how").value = "";
                document.getElementById("when").value = "";

                localStorage.setItem('what', '');
                localStorage.setItem('how', '');
                localStorage.setItem('when', '');
            }

            function resetValues(){

                document.getElementById("what").value = "";
                document.getElementById("how").value = "";
                document.getElementById("when").value = "";

                localStorage.setItem('what', '');
                localStorage.setItem('how', '');
                localStorage.setItem('when', '');
                deadline = 0 ;
                localStorage.setItem("deadline", deadline);


            }

            document.getElementById("what").value = getSavedValue("what");
            document.getElementById("how").value = getSavedValue("how");
            document.getElementById("when").value = getSavedValue("when");


            // to prevent default required alert from showing up
            document.addEventListener('invalid', (function () {
          return function (e) {
            e.preventDefault();
            document.getElementById("what").focus();
            document.getElementById("how").focus();
            document.getElementById("when").focus();

              };
            })(), true);

function tempAlert(msg,duration)
{
     var el = document.createElement("div");
     el.setAttribute("style","position:absolute;top:40%;left:20%;background-color:white;");
     el.innerHTML = msg;
     setTimeout(function(){
      el.parentNode.removeChild(el);
     },duration);
     document.body.appendChild(el);
}

</script>

<script>
var slideIndex = 0;
carousel();

function carousel() {
  var i;
  var x = document.getElementsByClassName("mySlides");
  for (i = 0; i < x.length; i++) {
    x[i].style.display = "none"; 
  }
  slideIndex++;
  if (slideIndex > x.length) {slideIndex = 1} 
  x[slideIndex-1].style.display = "block"; 
  setTimeout(carousel, 2000); 
}
</script>


<script>
document.addEventListener('click', click);

function click(e) {
  let el
  
  el = e.target
  
  if (el !== e.currentTarget) {
    if(el.nodeName === 'BUTTON') {
      
       if(el.classList.contains('is-active')) {
         el.classList.remove('is-active')
       } else {
         el.classList.add('is-active')
       }
    }
  }
  event.stopPropagation()
}
    </script>

</body>
</html>