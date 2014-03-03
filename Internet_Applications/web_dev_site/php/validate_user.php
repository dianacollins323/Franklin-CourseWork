<html>
<head>
    <title>validate user</title>
    <link href="../css/login.css" rel="stylesheet">
</head>
<body>
    <div id="page-content-wrapper">
    <?php
        include 'config.php';
        include 'opendb.php';

        $user = strip_tags($_POST['user']);
        $pass = strip_tags($_POST['password']);
        $errors = array();

        if (empty($user)) {
            $errors[] = 'Please enter your user name';
        }

        if (empty($pass)) {
            $errors[] = 'Please enter your password';
        }
        else {
            $reverse = "asjk" . strrev($pass);
            $pass = md5($reverse);
        }

        if (empty($errors)) {
            $query = "SELECT * FROM users";
            $result = mysql_query($query);

            while ($row = mysql_fetch_array($result)) {
                if ($user == $row['userName']) {
                    $id = $row['userId'];
                }
            }

            if ($id != 0) {
                $query = "SELECT * FROM users WHERE userId = '$id'";
                $result = mysql_query($query);
                while ($row = mysql_fetch_array($result)) {
                    $password = $row['password'];
                    if ($pass != $password) {
                        $errors[] = "Invalid Password";
                        echo "<h1>Login Error</h1>";
                        for ($i=0;$i<=sizeof($errors);$i++) {
                           echo "<div class=\"errors\">" . $errors[$i] . "</div>";
                        }
                        echo "<div class=\"errors\"><form method=\"link\" action=\"../user_login.php\"><input type=\"submit\" value=\"Back to Login\"></form>";
                    }
                    else {
                        //remove $id assignment
                        //$id = $row['userId'];
                        session_start();
                        //store userId in session
                        $_SESSION['userId'] = $row['userId'];
                        //remove id in url
                        header("Location: ../index.php");
                    }
                }
            }
            else {
                $errors[] = "Invalid User Name";
                echo "<h1>Login Error</h1>";
                for ($i=0;$i<sizeof($errors);$i++) {
                   echo "<div class=\"errors\">" . $errors[$i] . "</div>";
                }
                echo "<div class=\"errors\"><form method=\"link\" action=\"../user_login.php\"><input type=\"submit\" value=\"Back to Login\"></form>";
            }
        }
        else {
            echo "<h1>Login Error</h1>";
            for ($i=0;$i<sizeof($errors);$i++) {
               echo "<div class=\"errors\">" . $errors[$i] . "</div>";
            }
            echo "<div class=\"errors\"><form method=\"link\" action=\"../user_login.php\"><input type=\"submit\" value=\"Back to Login\"></form>";
        }

    ?>
    </div>
</body>
</html>