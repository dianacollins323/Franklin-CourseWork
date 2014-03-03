<?php
    session_start();
?>
<html>
    <head>
		<title>Step 4 Session Info</title>
		<link rel="stylesheet" type="text/css" href="step_4_style.css" />
	</head>
	<body>
		<div id="session">
			<h1>Step 4 Session Info</h1>
			<label>First Name:</label>
			<span><?php echo $_SESSION['first']; ?></span><br />
			
			<label>Last Name:</label>
			<span><?php echo $_SESSION['last']; ?></span><br />
			
			<label>Age:</label>
			<span><?php echo $_SESSION['userAge']; ?></span><br />
        </div>
	</body>
</html>
		