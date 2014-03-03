<?php
    include 'config.php';
    include 'opendb.php';
    include 'get_records.php';
?>
<html>
<head>
    <title>Guest Book</title>
    <link rel="stylesheet" href="guestbook.css" type="text/css" />
    <script src="http://code.jquery.com/jquery-latest.js"></script>
    
    <script type="text/javascript">
        function validate() {
            var errormessage = "";
            var em = document.myForm.email.value;
         
           if(document.myForm.fname.value == "") {
                errormessage += "\n Please provide your first name!";
           }

           if(document.myForm.lname.value == "") {
                errormessage += "\n Please provide your last name!";
           }

           if(!validateEmail(em)) {
                errormessage += "\n Please provide a valid email address!";
           }

           if(document.myForm.comments.value.trim() == "") {
               errormessage += "\n Please enter your comments!";
           }

            if(errormessage != ""){
                alert(errormessage);
                return false;
            }
            else {
                return true;
            }
        }
        function validateEmail(email) { 
            var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
            return re.test(email);
        } 
    </script>
</head>
<body>
    <form method="post" action="add_guest.php" id="guestbook" name="myForm" onsubmit="return(validate());">
        <fieldset>
            <legend>Please sign our guestbook!</legend>
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
                    <input type="text" id="id_email" name="email" /> 
                </li>
                <li>
                    <label for:"Comments">
                        Comments
                    </label>
                    <textarea id="id_comments" name="comments" rows="10" cols="40">
                    </textarea> 
                </li>
                <li>
                    <input type="submit" id="submit" name="submit" value="Submit" /> 
                </li>
            </ul>
        </fieldset>
    </form>
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
    <p><a href="<?php echo $_SERVER['PHP_SELF'];?>">List of Guests</a></p>
    </div>
</body>
</html>
<?php
#}
?>