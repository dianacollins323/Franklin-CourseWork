<?php
    include 'config.php';
    include 'opendb.php';
    include 'get_records.php';
?>
<html>
<head>
    <title>Guest Book</title>
    <link rel="stylesheet" href="guestbook.css" type="text/css" />
    <script src="../validation_2/lib/jquery.js"></script>
    <script src="jquery.validate.js"></script>
    
</head>
<body>
    <form method="post" action="add_guest.php" id="guestbook" name="myForm">
        <fieldset>
            <legend>Please sign our guestbook!</legend>
            <ul>
                <li>
                    <label for:"First Name">
                        First Name
                    </label>
                    <input id="cname" name="fname" minlength="2" type="text" required/> 
                </li>
                <li>
                    <label for:"Last Name">
                        Last Name
                    </label>
                    <input id="cname" name="lname" minlength="2" type="text" required/> 
                </li>
                <li>
                    <label for:"Email">
                        Email
                    </label>
                    <input id="cemail" type="email" name="email" required/> 
                </li>
                <li>
                    <label for:"Comments">
                        Comments
                    </label>
                    <textarea id="ccomments" name="comments" rows="10" cols="40"  required>
                    </textarea> 
                </li>
                <li>
                    <input type="submit" id="submit" name="submit" value="Submit" /> 
                </li>
            </ul>
        </fieldset>
    </form>
    <script type="text/javascript">
        $("#guestbook").validate();
    </script>
    <div>
        <p><a href="admin_view.php">Admin View</a></p>
        <h3>Guestbook Entries</h3>
<?php
        echo $details;
        if(isset($_GET['id'])) {
?>
    
<?php
    }
?>
    
    </div>
</body>
</html>
<?php
#}
?>