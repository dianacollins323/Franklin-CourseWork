<html>
<head>
    <title>Contact Form</title>
</head>
<body>
<?php
    $recipient = strip_tags($_POST['recipient']);
    $name = strip_tags($_POST['name']);
    $email = strip_tags($_POST['email']);
    $comment = strip_tags(trim($_POST['message']));
    $id = $_GET['id'];

    if (empty($recipient)) {
        $errors[] = 'Please choose a recipient.';
    }

    if (empty($name)) {
        $errors[] = 'Please enter your full name.';
    }

    if (empty($email)) {
        $errors[] = 'Please enter an e-mail.';
    }
    else if (!eregi("^[_a-z0-9-]+(.[_a-z0-9-]+)*@[a-z0-9-]+(.[a-z0-9-]+)*(.[a-z]{2,3})$", $email)) {
        $errors[] = 'Please enter a valid e-mail address.';
    }

    if (empty($comment)) {
        $errors[] = 'Please enter a message.';
    }
    else if (strlen ($coment) > 255) {
        $errors[] = 'Your comment is too long, please do not submit more then 255 characters.';
    }

    if (count($errors) == 0) {
        if ($recipient == "teacher") {
            $emailto = 'teacher@ccs.com';
            $body = 'Name: $name <br> Email: $email <br> Message: $comment';
            $headers = 'From: Student Teacher<'.$emailto.'>' . "\r\n" . 'Reply-To: ' .$email . "\r\n" . 'X-Mailer: PHP/' . phpversion();
            @mail($emailto, $body, $headers);
            $emailSent = true;
        }
        elseif ($recipient == "principal") {
            $emailto = 'principal@ccs.com';
            $body = 'Name: $name <br> Email: $email <br> Message: $comment';
            $headers = 'From: School Principal<'.$emailto.'>' . "\r\n" . 'Reply-To: ' .$email . "\r\n" . 'X-Mailer: PHP/' . phpversion();
            @mail($emailto, $body, $headers);
            $emailSent = true;
        }
        elseif ($recipient == "counselor") {
            $emailto = 'counselor@ccs.com';
            $body = 'Name: $name <br> Email: $email <br> Message: $comment';
            $headers = 'From: Guidance Counselor<'.$emailto.'>' . "\r\n" . 'Reply-To: ' .$email . "\r\n" . 'X-Mailer: PHP/' . phpversion();
            @mail($emailto, $body, $headers);
            $emailSent = true;
        }
        else {
            $emailto = 'super@ccs.com';
            $body = 'Name: $name <br> Email: $email <br> Message: $comment';
            $headers = 'From: CCS Superintendent<'.$emailto.'>' . "\r\n" . 'Reply-To: ' .$email . "\r\n" . 'X-Mailer: PHP/' . phpversion();
            @mail($emailto, $body, $headers);
            $emailSent = true;
        }
        echo "<p>Your email has been sent.</p>";
        echo "<a href=\"../contact.php?id=" . $id . "\">Back to Contact Page</a>";
    }
    else {
        for ($i=0;$i<=4;$i++){
           echo "$errors[$i]<br>";
        }
        echo "<a href='../contact.php'>Back to Contact Page</a>";
    }
?>
</body>
</html>