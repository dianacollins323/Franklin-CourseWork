<!DOCTYPE html>
<html>
	<head>
        <link rel="stylesheet" type="text/css" href="step_2_style.css" />
<?php
    $title;
	$cnt = 0;
	$firstName = $_GET['firstName'];
	$lastName = $_GET['lastName'];
	$age = $_GET['age'];
	
	if ($firstName == null) {
		$cnt++;
	}
	if ($lastName == null) {
		$cnt++;
	}
	if ($age == null) {
		$cnt++;
	}
    if ($cnt > 0) {
		$cnt = 0;
		$title = "Step 2 Form";
?>
        <title><?php echo $title ?></title>
    </head>
    <body>
		<div id="form">
			<h1>Step 2 Form</h1>
			<form action="<?php echo $PHP_SELF; ?>" method="get">
				<label>First Name:</label>
				<input type="text" name="firstName" value="<?php echo $firstName ?>"><br>
				
				<label>Last Name:</label>
				<input type="text" name="lastName" value="<?php echo $lastName ?>"><br>
				
				<label>Age:</label>
				<input type="text" name="age" value="<?php echo $age ?>"><br>
			
				<label>&nbsp;</label>
				<input type="submit" name="submit" value="Submit" id="btn">
			</form>
		</div>
<?php
	}
	else {
		$title = "Step 2 Feedback";
?>
		<title><?php echo $title ?></title>
	</head>
	<body>
		<div id="feedback">
			<h1>Step 2 Feedback</h1>
			<label>First Name:</label>
			<span><?php echo $firstName; ?></span><br />
			
			<label>Last Name:</label>
			<span><?php echo $lastName; ?></span><br />
			
			<label>Age:</label>
			<span><?php echo $age; ?></span><br />
		</div>
	<?php
    }
    ?>
    </body>
</html>