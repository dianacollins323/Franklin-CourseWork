<?php
    session_start();
?>
<html>
    <head>
		<title>Step 5 Session Info</title>
		<link rel="stylesheet" type="text/css" href="step_5_style.css" />
	</head>
	<body>
		<div id="session">
			<h1>Your Christmas Tree</h1>
<?php
    $size = $_SESSION['size'];
    $space = "&nbsp;";
    $star = "*";
    
    for ($i = 1; $i <= $size; $i++) {
        $whtSp = $size - $i;
		$stNum = 2 * $i - 1;
		
		for ($w = 0; $w < $whtSp; $w++) {
		    echo $space;
		}
	    
	    for ($s = 0; $s < $stNum; $s++) {
	        echo $star;
	    }
	    
	    echo "<br>";	
	    #echo $whtSp . ", " . $stNum . "<br>";
    }
    
    for ($w = 0; $w < $size - 1; $w++) {
        echo $space;
    }
    
    echo $star;
?>
		</div>
	</body>
</html>