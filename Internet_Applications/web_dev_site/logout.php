<?php
    //continue session here
    session_start();
    session_destroy();
    //unset userId in session
    unset($_SESSION['userId']);
    header("Location: user_login.php");
?>