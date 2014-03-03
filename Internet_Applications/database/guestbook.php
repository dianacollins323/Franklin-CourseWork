<?php
    include 'config.php';
    include 'opendb.php';
    include 'get_records.php';
?>
<html>
<head>
    <title>Guest Book</title>
    <link rel="stylesheet" href="guestbook.css" type="text/css" />
    <script src="../database/lib/jquery.js"></script>
    <script src="jquery.validate.js"></script>
    <script type="text/javascript">
        function getSummary(id) {
           $.ajax({

             type: "GET",
             url: 'get_records.php',
             data: "id=" + id, // appears as $_GET['id'] @ ur backend side
             success: function(data) {
                   // data is ur summary
                  $('#responseContainer').html(data);
             }

           });

        }
    </script>
</head>
<body>
    <div id="main">
    <form method="post" action="add_guest.php" id="guestbook" name="myForm">
        <div id="formHeader">Please sign our guestbook!</div>
            <div class="form">
                <label for:"First Name">
                    First Name *
                </label>
                <input id="cname" name="fname" minlength="2" type="text" required/> 
            </div>
            <div class="form">
                <label for:"Last Name">
                    Last Name *
                </label>
                <input id="cname" name="lname" minlength="2" type="text" required/> 
            </div>
            <div class="form">
                <label for:"Email">
                    Email *
                </label>
                <input id="cemail" type="email" name="email" required/> 
            </div>
            <div class="form">
                <label for:"Comments">
                    Comments *
                </label>
                <textarea id="ccomments" name="comments" rows="10" cols="40"  required>
                </textarea> 
            </div>
            <div class="form">
                <input type="submit" id="submit" name="submit" value="Submit" /> 
            </div>
            <div class="form">* Required    <a href="admin_view.php">Admin View</a></div>
    </form>
    <script type="text/javascript">
        $("#guestbook").validate();
    </script>
    <div id="responseContainer" class="form">
<?php
        if(isset($_GET['id'])) {
            echo "<h3>Record Details</h3>";
            echo showDetails();
            echo "<p><a href=\"guestbook.php\">List of Guests</a></p>";
        }
        else {
            echo "<h3>Last 5 Entries</h3>";
            echo lastFive();
        }
?>
    </div>
</div>
</body>
</html>
<?php
#}
?>