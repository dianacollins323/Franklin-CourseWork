<?php
    include 'config.php';
    include 'opendb.php';
    include 'get_records.php';

    if(isset($_GET['del'])) {
    	$query = "DELETE FROM tblguestbook WHERE ID = '{$_GET['del']}'";
    	mysql_query($query) or die('Error: ' . mysql_error());
    	header('Location: ' . $_SERVER['PHP_SELF']);
    	exit;
    }
?>
<html>
<head>
	<title>Admin Page</title>
	<link rel="stylesheet" href="guestbook.css" type="text/css" />
    <script src="../validation_2/lib/jquery.js"></script>
    <script src="jquery.validate.js"></script>
	<script type="text/JavaScript">
        function delConfirm($id, $first, $last) {
        	if (confirm("Are you sure you want to delete the record for " + $first + " " + $last + "?")) {
        		window.location.href = 'admin_view.php?del=' + $id;
        	}
        }
        function getSummary(id) {
           $.ajax({

             type: "GET",
             url: 'get_records.php',
             data: "id=" + id,
             success: function(data) {
                  $('#responseContainer').html(data);
             }

            });
        }
	</script>
</head>
<body>
    <div id="adminMain">
<?php  
    $self = $_SERVER['PHP_SELF'];  
    $query = "SELECT ID, first, last, comment FROM tblguestbook ORDER BY ID";
    $result = mysql_query($query) or die('Error: ' . mysql_error());
?>
    <table align="center">
        <tr>
            <th>Name</th>
            <th>Comments</th>
            <th>Action</th>
        </tr>
<?php
    while(list($id, $first, $last, $comment) = mysql_fetch_array($result, MYSQL_NUM)) {
?>
        <tr>
            <td><?php echo $id . " " . $first . " " . $last;?></td>
            <td><?php echo $comment; ?></td>
            <td>
<?php
                echo "<a href=\"$self?id=" . $id . "\">View</a> |";
                echo "<a href=\"javascript:delConfirm('" . $id . "', '" . $first . "', '" .  $last . "');\">Delete</a>";
?>
            </td>
        </tr>
<?php
    }
?>
        <tr>
        	<td colspan="3" id="link">
               <a href="guestbook.php">Back to Guestbook</a>
            </td>
        </tr>
    </table>
    <div id="responseContainer">
<?php
        if(isset($_GET['id'])) {
            echo "<h3>Record Details</h3>";
            echo showDetails();
        }
?>
    </div>
</div>
</body>
</html>