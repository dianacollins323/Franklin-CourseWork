<?php
if(!isset($_GET['id']))
{
	$self = $_SERVER['PHP_SELF'];
	
	$query = "SELECT ID as userid,first,last FROM tblguestbook ORDER BY ID DESC LIMIT 5";

	$result = mysql_query($query) or die ('Error : ' .mysql_error());

	$details = '<ol>';
	while($row = mysql_fetch_array($result))
	{
		$details .= "<li><a href=\"$self?id=$row[userid]\">$row[first] $row[last] </a></li>\r\n";
	}
	$details .= '</ol>';
	$title = 'Guest Book Entries';
}
else {
	$query = "SELECT * FROM tblguestbook WHERE ID = '{$_GET['id']}'";
	$result = mysql_query($query) or die ('Error : ' .mysql_error());

	while($row = mysql_fetch_array($result))
	{
		$title = $row['first']. " " . $row['last'];
		$details =  "<br>First Name: " . $row['first'].
                    "<br>Last Name: " . $row['last'].
                    "<br>Email: " . $row['email'].
					"<br>IP: " . $row['ip'].
					"<br>OS: " . $row['os'].
					"<br>Browser: " . $row['browser'].
					"<br>Comment: " . $row['comment'].
					"<br>Date: " . $row['DateAdded'].
					"<p><a href=\"guestbook.php\">List of Guests</a></p>";
	}

}
?>