<html>
<head>
    <title>Contact Us</title>
    <link rel="stylesheet" href="contactform.css" type="text/css" />
</head>
<body>
    <h2>Contact Us</h2>
    <?php
        if (isset($emailSent) && $emailSent == true) { ?>
        	<p>Your message has been sent.</p>
    	    <p>Thank you, <?php echo $fname; ?>, for your email.
    <?php } else { ?>
    <form method="post" action="procedural_val.php" id="contactform">
        <fieldset>
            <legend>Contact Form</legend>
            <ul>
                <li>
                    <label for:"First Name">
                    	First Name
                    </label>
                    <input type="text" id="id_fname" name="fname" /> 
                </li>
                <li>
                    <label for:"Last Name">
                    	Last Name
                    </label>
                    <input type="text" id="id_lname" name="lname" /> 
                </li>
                <li>
                    <label for:"Email">
                    	Email
                    </label>
                    <input type="email" id="id_email" name="email" /> 
                </li>
                <li>
                    <label for:"Subject">
                    	Subject
                    </label>
                    <input type="text" id="id_sub" name="sub" /> 
                </li>
                <li>
                    <label for:"Message">
                    	Message
                    </label>
                    <textarea id="id_mess" name="mess" rows="10" cols="40">
                    </textarea> 
                </li>
                <li>
                    <input type="submit" id="submit" name="submit" value="Submit" /> 
                </li>
            </ul>
        </fieldset>
    </form>
    <?php } ?>
</body>
</html>