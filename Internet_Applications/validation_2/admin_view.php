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
	<script type="text/JavaScript">
        function delConfirm($id, $first, $last) {
        	if (confirm("Are you sure you want to delete the record?")) {
        		window.location.href = 'admin_view.php?del=' + $id;
        	}
        }
	</script>
</head>
<body>
<?php    
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
                <a href="guestbook.php?$id=<?php echo $id; ?>">View</a> |
                <a href="javascript:delConfirm('<?php echo $id; ?>', '<?php echo $first; ?>', '<?php echo $last; ?>');">
                	Delete
                </a>
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
    <div>
<?php
        if(isset($_GET['id'])) {
?>
    
<?php
    }
?>
    
    </div>
</body>
</html>