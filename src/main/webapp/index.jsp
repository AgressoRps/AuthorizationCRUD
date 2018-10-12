<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" type="text/css" href="css/index.css">
    <script type="text/javascript"src="js/modal.js"></script>
</head>
<body>

    <h2 style="text-align: center;">"Sign Up" and "Sign In" Forms</h2>

    <button onclick="document.getElementById('id01').style.display='block'" >Sign Up</button>
    <button onclick="document.getElementById('id02').style.display='block'">Sign In</button>

    <!-- Modal Window 1 -->

    <div id="id01" class="modal">
      <span onclick="document.getElementById('id01').style.display='none'" class="close" title="Close Modal">&times;</span>
      <form class="modal-content" action="/action_page.php">
        <div class="container">
          <h1>Sign Up</h1>
          <p>Please fill in this form to create an account.</p>
          <hr>
          <label for="name"><b>Name</b></label>
          <input type="text" placeholder="Enter Name" name="name" required>
          <label for="name"><b>Email</b></label>
          <input type="text" placeholder="Enter Email" name="name" required>
          <label for="city"><b>City</b></label>
          <select name="city" id="city">
            <option value="Kiev">Kiev</option>
            <option value="Kiev">Moscow</option>
          </select>
          <label for="psw"><b>Password</b></label>
          <input type="password" placeholder="Enter Password" name="psw" required>

          <label for="psw-repeat"><b>Repeat Password</b></label>
          <input type="password" placeholder="Repeat Password" name="psw-repeat" required>

          <p>By creating an account you agree to our <a href="#" style="color:dodgerblue">Terms & Privacy</a>.</p>

          <div class="clearfix">
            <button type="button" onclick="document.getElementById('id01').style.display='none'" class="cancelbtn">Cancel</button>
            <button type="submit" class="signupbtn">Sign Up</button>
          </div>
        </div>
      </form>
    </div>

    <!-- Modal Window 2 -->

    <div id="id02" class="modal">
      <span onclick="document.getElementById('id02').style.display='none'" class="close" title="Close Modal">&times;</span>
      <form class="modal-content" action="/action_page.php">
        <div class="container">
          <h1>Sign In</h1>
          <p>Please enter your details.</p>
          <hr>
          <label for="email"><b>Email</b></label>
          <input type="text" placeholder="Enter Email" name="email" required>

          <label for="psw"><b>Password</b></label>
          <input type="password" placeholder="Enter Password" name="psw" required>

          <label>
            <input type="checkbox" checked="checked" name="remember" style="margin-bottom:15px"> Remember me
          </label>

          <p id="error">Error, invalid data entered.</p>

          <div class="clearfix">
            <button type="button" onclick="document.getElementById('id02').style.display='none'" class="cancelbtn">Cancel</button>
            <button type="submit" class="signupbtn">Sign In</button>
          </div>
        </div>
      </form>
    </div>
</body>
</html>