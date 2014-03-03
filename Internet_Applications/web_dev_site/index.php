<?php
    session_start();
    //check if session exist
    if (!isset($_SESSION['userId'])) {
        header("Location: php/invalid_user.php");
    }
    else {
        include 'php/config.php';
        include 'php/opendb.php';
        include 'php/get_info.php';
?>
<html lang="en">
<head>
    <title>Harter Hawks</title>
    <link rel="shortcut icon" href="images/favicon_<?php echo schoolCss(); ?>.ico" />
    <link href="css/<?php echo schoolCss(); ?>.css" rel="stylesheet">
    <!--[if IE]>
        <link href="css/ie_css/<?php echo schoolCss(); ?>.css" rel="stylesheet">
    <![endif]-->
</head>
<body>
    <div id="wrapper">
        <div id="header-cont">
            <a href="#"><img src="images/logo_<?php echo schoolCss(); ?>.png" alt="Hawk" id="header-image"></a>
            <h1 id="header">
                <?php echo schoolHeader(); ?>
            </h1>
        </div>
        <div id="menuContainer">
            <ul class="sidebar-nav">
                <!--remove id in url for all links-->
                <li class="menu" id="first"><a href="index.php">Home</a></li>
                <li class="menu"><a href="schedule.php">Schedule</a></li>
                <li class="menu"><a href="grades.php">Grades</a></li>
                <li class="menu"><a href="discipline.php">Discipline</a></li>
                <li class="menu"><a href="forms.php">Forms</a></li>
                <li class="menu"><a href="calendar.php">Calendar</a></li>
                <li class="menu"><a href="contact.php">Contact</a></li>
                <li class="menu"><a href="logout.php">Logout</a></li>
            </ul>
        </div>
        <div id="contentContainer">
            <h1 id="content-header"><?php echo studentHeader(); ?></h1>
            <table id="homeContent">
                <tr>
                  <td class="left">School District: </td>
                  <td class="right">Canton City Schools</td>
                </tr>
                <?php echo homeInfo(); ?>
            </table>
        </div>
    </div>
</body>
</html>
<?php
    }
?>