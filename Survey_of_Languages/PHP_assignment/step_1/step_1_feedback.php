<?php
    $firstName = $_GET['firstName'];
    $lastName = $_GET['lastName'];
    $age = $_GET['age'];
?>
<!DOCTYPE html>
<html>
    <head>
        <title>Step 1 Feedback</title>
        <link rel="stylesheet" type="text/css" href="step_1_style.css" />
    </head>
    <body>
        <div id="main_cont">
            <h1>Step 1 Feedback</h1>
            <label>First Name:</label>
            <span><?php echo $firstName; ?></span><br />
            
            <label>Last Name:</label>
            <span><?php echo $lastName; ?></span><br />
            
            <label>Age:</label>
            <span><?php echo $age; ?></span><br />
        </div>
    </body>
</html>