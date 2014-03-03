<html>
<head>
    <link rel="stylesheet" href="guestbook.css">
    <title>Add Guest</title>
</head>
<body>
<?php
    include 'config.php';
    include 'opendb.php';
    include "get_records.php";

    $fname = strip_tags($_POST['fname']);
    $lname = strip_tags($_POST['lname']);
    $email = strip_tags($_POST['email']);
    $comment = strip_tags($_POST['comments']);

    if (empty($fname)) {
        $errors[] = 'Please enter your first name';
    }

    if (empty($lname)) {
        $errors[] = 'Please enter your last name';
    }

    if (empty($email)) {
        $errors[] = 'Please enter an e-mail';
    }
    else if (!eregi("^[_a-z0-9-]+(.[_a-z0-9-]+)*@[a-z0-9-]+(.[a-z0-9-]+)*(.[a-z]{2,3})$", $email)) {
        $errors[] = 'Please enter a valid e-mail address';
    }

    if (empty($comment)) {
        $errors[] = 'Please enter some comments';
    }
    else if (strlen ($coment) > 255) {
        $errors[] = 'Your comment is too long, please do not submit more then 255 characters';
    }

    if (count($errors) == 0)
    {
        echo "<fieldset>";
        echo "<legend>1 Record has been added to the guestbook!</legend>";
        $ipAddress = $_SERVER['REMOTE_ADDR'];
        echo '<p>Your IP address: ' . $ipAddress . '</p>';
        include_once "inc_getparsedcgivars.php";
        $browser = get_user_browser();
        $os = get_user_os();
        $query="INSERT INTO tblguestbook (email, ip, os,  browser, DateAdded, last, first, comment)
                VALUES ('$email', '$ipAddress', '$os', '$browser', CURRENT_TIMESTAMP(), '$lname', '$fname', '$comment')";

        $result=mysql_query($query) or die('Error : ' . mysql_error());

        
        echo "<a href='guestbook.php'>Back to Guest Book</a>";
        echo "</fieldset>";
    }
    else
    {
        for ($i=0;$i<=4;$i++){
           echo "$errors[$i]<br>";
        }
        echo "<a href='guestbook.php'>Back to Guest Book</a>";
    }
    ?>
</body>
</html>