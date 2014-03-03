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
    <script src="javascript/jquery.js"></script>
    <script src="javascript/jquery.validate.js"></script>
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
            <form method="post" action="php/validate_form.php?id=<?php echo $id; ?>" id="contactForm" name="myForm">
                <div id="formHeader" class="formDiv">Please fill in the form below!</div>
                    <div class="form formDiv">
                        <label for:"Message Recipient">
                            Choose 1 Message Recipient *
                        </label>
                        <table>
                            <tr>
                                <td class="right"><input type="radio" name="recipient" value="teacher" class="required"> Student Teacher </td>
                                <td class="right"><input type="radio" name="recipient" value="principal"> School Principal </td>
                            </tr>
                            <tr>
                                <td class="right"><input type="radio" name="recipient" value="counselor"> Guidance Counselor </td>
                                <td class="right"><input type="radio" name="recipient" value="super"> CCS Superintendent </td>
                            </tr>
                        </table>
                    </div>
                    <div class="form formDiv">
                        <label for:"Full Name">
                            Full Name *
                        </label>
                        <input id="cname" name="name" minlength="2" type="text" required/> 
                    </div>
                    <div class="form formDiv">
                        <label for:"Email">
                            Email *
                        </label>
                        <input id="cemail" type="email" name="email" required/> 
                    </div>
                    <div class="form formDiv">
                        <label for:"Message">
                            Message *
                        </label>
                        <textarea id="ccomments" name="message" rows="10" cols="40"  required>
                        </textarea> 
                    </div>
                    <div class="form formDiv">
                        <input type="submit" id="submit" name="submit" value="Submit" /> 
                    </div>
                    <div class="form formDiv">* Required Field</div>
            </form>
            <script type="text/javascript">
                $("#contactForm").validate();
            </script>
        </div>
    </div>
</body>
</html>
<?php
    }
?>