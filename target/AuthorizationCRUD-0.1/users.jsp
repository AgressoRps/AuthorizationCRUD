<!DOCTYPE html>
<html>
<head>
  <link rel="stylesheet" href="css/users.css">
  <script type="text/javascript" src="js/edit.js"></script>
</head>
<body>

  <p>Enter name or email:</p>
  <form>
    <input id="search" type="text" id="search" name="search" placeholder="Search..">
    <button type="submit" name="submit">Search</button>
  </form>

  <table id="customers">
    <tr>
      <th>Id</th>
      <th>Name</th>
      <th>Email</th>
      <th>Country</th>
      <th>Edit</th>
      <th>Delete</th>
    </tr>
    <tr>
      <td>1</td>
      <td>Vladislav</td>
      <td>AgressoRj@gmail.com</td>
      <td>Germany</td>
      <td>
        <span onclick="showEdit(id)">
          <img src="img/pencil.svg" alt="edit">
        </span>
      </td>
      <td>
        <a href="#">
          <img src="img/delete.svg" alt="delete">
        </a>
      </td>
    </tr>
  </table>

  <div id="id01" class="modal">
    <span onclick="document.getElementById('id01').style.display='none'" class="close" title="Close Modal">&times;</span>
    <form class="modal-content" action="/action_page.php">
      <div class="container">
        <h1>Edit User</h1>
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

        <div class="clearfix">
          <button type="button" onclick="document.getElementById('id01').style.display='none'" class="cancelbtn">Cancel</button>
          <button type="submit" class="savebtn">Save</button>
        </div>
      </div>
    </form>
  </div>

</body>
</html>
