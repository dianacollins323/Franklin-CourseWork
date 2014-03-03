<?php

if (isset($_POST['submit'])) {

    if (trim($_POST['fname'] == '')) {
    	$errors[] = 'Please enter your first name.';
    }
    else {
    	$fname = trim($_POST['fname']);
    }

    if (trim($_POST['lname'] == '')) {
    	$errors[] = 'Please enter your last name.';
    }
    else {
    	$lname = trim($_POST['lname']);
    }

    if (trim($_POST['email'] == '') || !filter_var($_POST['email'], FILTER_VALIDATE_EMAIL)) {
    	$errors[] = 'Please enter a valid email address.';
    } 
    else {
    	$email = trim($_POST['email']);
    }

    if (trim($_POST['sub'] == '')) {
    	$errors[] = 'Please enter a subject.';
    }
    else {
    	$lname = trim($_POST['sub']);
    }

    if (trim($_POST['mess']) == '') {
        $errors[] = 'Please enter a comment in the message area.';
    }
    else {
    	$mess = trim($_POST['mess']);
    }

    if (count($errors) > 0) {
        for ($i = 0; $i < count($errors); $i++) {
        	echo "$errors[$i]<br>";
        }
        echo "<a href='javascript:history.back(1);'>Back to Contact Form</a>";
    }
    else{
    	$emailto = 'dianacollins323@gmail.com';
        $body = 'Name: $fname $lname <br> Email: $email <br> Subject: $subject <br> Message: $mess';
        $headers = 'From: Diana Collins<'.$emailto.'>' . "\r\n" . 'Reply-To: ' .$email . "\r\n" . 'X-Mailer: PHP/' . phpversion();
        @mail($emailto, $body, $headers);
        $emailSent = true;
    	echo "Your message has been sent. <br>";
    	echo "Thank you, $fname, for your email. <br>";
    	echo "<a href='contactform.php'>Back to Contact Form</a>";
    	//echo "<a href='javascript:history.back(1);'>Back to Contact Form</a>";
    }


}

?>