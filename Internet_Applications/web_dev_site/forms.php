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
            <table id="formsContent">
                <tr>
                    <td class="centerAll"><a href="forms/4___Media_Appearance_Release_Form_Robyn_7_3_13.pdf" 
                        target="_blank">Media Appearance Release</a></td>
                    <td class="centerAll"><a href="forms/Directory_Information.pdf" target="_blank">
                        Directory Information</a></td>
                    <td class="centerAll"><a href="forms/Consent_for_Records_Release.pdf" target="_blank">
                        Records Release Consent</a></td>
                </tr>
                <tr>
                    <td class="centerAll"><a href="forms/Dismissal_Form_Final.pdf" target="_blank">
                        Dismissal Information</a></td>
                    <td class="centerAll"><a href="forms/Emergency_Closing_Form_Policy_EBCD.pdf" target="_blank">
                        Emergency Closing Policy</a></td>
                    <td class="centerAll"><a href="forms/Emergency_Medical_Health_Authorization.pdf" target="_blank">
                        Emergency Medical Authorization</a></td>
                </tr>
                <tr>
                    <td class="centerAll"><a href="forms/Home_Language_Survey_2013-14f_2.pdf" target="_blank">
                        Home Language Survey</a></td>
                    <td class="centerAll"><a href="forms/Information_Regarding_Legal_Custody_2013-14_f_2.pdf" 
                        target="_blank">Legal Custody Information</a></td>
                    <td class="centerAll"><a href="forms/School_Parent_Student_Compact.pdf" target="_blank">
                        School/Parent/Student Agreement</a></td>
                </tr>
            </table>
        </div>
    </div>
</body>
</html>
<?php
    }
?>