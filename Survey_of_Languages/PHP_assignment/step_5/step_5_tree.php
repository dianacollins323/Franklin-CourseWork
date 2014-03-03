<?php 
    session_start(); 
    $_SESSION['size'];
?>
<!DOCTYPE html>
<html>
	<head>
	    <link rel="stylesheet" type="text/css" href="step_5_style.css" />
<?php
    $title;
	$cnt = 0;
	$tree = $_GET['tree'];
	
	if ($tree == null) {
		$cnt++;
	}
    if ($cnt > 0) {
		$cnt = 0;
		$title = "Step 5 Form";
?>
        <title><?php echo $title ?></title>
    </head>
    <body>
		<div id="form">
			<h1>What Size?</h1>
			<form action="<?php echo $PHP_SELF; ?>" method="get">
				<label>Size of Your Christmas Tree:</label>
				<input type="text" name="tree" value="<?php echo $tree ?>"><br>
			
				<label>&nbsp;</label>
				<input type="submit" name="submit" value="Submit" id="btn">
			</form>
		</div>
<?php
	}
	else {
		$title = "Step 5 Feedback";
		$_SESSION['size'] = $tree;
?>
		<title><?php echo $title ?></title>
	</head>
	<body>
		<div id="feedback">
			<h1>Your Tree Size</h1>
			<label>Your Christmas tree is <?php echo $tree; ?> high!</label>
			
			<a href="check_session.php">Click Here to see it!</a>
		</div>
	<?php
    }
    ?>
    </body>
</html>