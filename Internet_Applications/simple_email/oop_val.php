<?php

  if(isset($_POST['submit'])) {
 
    //include validation class
    include('validate.class.php');
 
    //assign post data to variables
    $fname = trim($_POST['fname']);
    $lname = trim($_POST['lname']);
    $email = trim($_POST['email']);
    $sub = trim($_POST['sub']);
    $message = trim($_POST['mess']);
    

    $v = new validate();
    $v->validateStr($fname, "fname", 3, 75);
    $v->validateStr($lname, "lname", 3, 75);
    $v->validateEmail($email, "email");
    $v->validateStr($sub, "sub", 3, 100);
    $v->validateStr($message, "mess", 5, 1000);
 
    if(!$v->hasErrors()) {
        $emailto = 'dianacollins323@gmail.com';
        $body = 'Name: $fname $lname <br> Email: $email <br> Subject: $subject <br> Message: $mess';
        $headers = 'From: Diana Collins<'.$emailto.'>' . "\r\n" . 'Reply-To: ' .$email . "\r\n" . 'X-Mailer: PHP/' . phpversion();
        @mail($emailto, $body, $headers);
        $emailSent = true;
        echo "Your message has been sent. <br>";
        echo "Thank you, $fname, for your email. <br>";
        echo "<a href='oop_contactform.php'>Back to Contact Form</a>";
    }
    else {
        //set the number of errors message
        $message_text = $v->errorNumMessage();
 
        //store the errors list in a variable
        $errors = $v->displayErrors();
 
        //get the individual error messages
        $fnameErr = $v->getError("fname");
        $lnameErr = $v->getError("lname");
        $emailErr = $v->getError("email");
        $subErr = $v->getError("sub");
        $messageErr = $v->getError("mess");

        echo $fnameErr . "<br>" . $lnameErr . "<br>" . $emailErr . "<br>" . $subErr . "<br>" . $messageErr . "<br>";
        echo "<a href='javascript:history.back(1);'>Back to Contact Form</a>";
    }
  }
?>