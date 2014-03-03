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
    <script type="text/javascript" src="https://www.google.com/jsapi"></script>
    <script type='text/javascript' src='https://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js'>
    </script>
    <script type='text/javascript'>
        function GCalEvents() {
            /*get next 6 calendar events*/
            var calendar_json_url = "https://www.google.com/calendar/feeds/63r3u11u33av90cja6iushhfpk%40group.calendar.google.com/public/basic?orderby=starttime&sortorder=ascending&max-results=6&futureevents=true&alt=json";
            /*print next 6 calendar events in new tr and td tags*/
            jQuery.getJSON(calendar_json_url, function(data) {
                jQuery.each(data.feed.entry, function(i, item) {
                    /*split item.content.$t to retrieve only the date*/
                    var getDate = item.content.$t.split("<br");
                    var date = getDate[0].split(":");
                    jQuery("#gcal-events tr").last().after("<tr><td>" + date[1] + ": " + item.title.$t + "</td></tr>");
                })
            })
        }
    </script>
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
            <div id="calendarContainer">
                <iframe src="https://www.google.com/calendar/embed?height=300&amp;wkst=1&amp;bgcolor=%23ffffff&amp;src=63r3u11u33av90cja6iushhfpk%40group.calendar.google.com&amp;color=%23060D5E&amp;ctz=America%2FNew_York" style=" border-width:0 " width="300" height="300" frameborder="0" scrolling="no"></iframe>
            </div>
            <p id="eventHeader">Important Dates</p>
            <!--table to displat calendar events-->
            <table id="gcal-events">
                <tr></tr>
            </table>
        </div>
    </div>
    <script type="text/javascript" charset="utf-8">
        $(document).ready(GCalEvents());
    </script>
</body>
</html>
<?php
    }
?>