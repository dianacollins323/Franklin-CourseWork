<?php
//continue session here
session_start();

//retrieves css for specific school site
function schoolCss() {
    //get session userId
    $id = $_SESSION['userId'];
    $query = "SELECT schoolName FROM studentInfo WHERE userId = '$id'";
    $result = mysql_query($query) or die ('Error : ' .mysql_error());

    while($row = mysql_fetch_array($result)) {
        $school =  $row['schoolName'];
    }

    if ($school == "Harter Elementary") {
        $details = "harter";
    }
    elseif ($school == "Clarendon Elementary") {
        $details = "clarendon";
    }

    return $details;
}

//retrieves school name and returns it for page header
function schoolHeader() {
    //get session userId
    $id = $_SESSION['userId'];
    $query = "SELECT schoolName FROM studentInfo WHERE userId = '$id'";
    $result = mysql_query($query) or die ('Error : ' .mysql_error());
    
    while($row = mysql_fetch_array($result)) {
        $details =  $row['schoolName'];
    }
    return $details;
}

//retrieves student name and returns it for the content header
function studentHeader() {
    //get session userId
    $id = $_SESSION['userId'];
    $query = "SELECT studentName FROM studentInfo WHERE userId = '$id'";
    $result = mysql_query($query) or die ('Error : ' .mysql_error());
    
    while($row = mysql_fetch_array($result)) {
        $details = $row['studentName'];
    }
    return $details;
}

//retrieves and returns info to display on home page
function homeInfo() {
    //get session userId
    $id = $_SESSION['userId'];
    $query = "SELECT * FROM studentInfo WHERE userId = '$id'";
    $result = mysql_query($query) or die ('Error : ' .mysql_error());
    
    while($row = mysql_fetch_array($result)) {
        $details =  "<tr><td class=\"left\">School: </td><td class=\"right\">" . $row['schoolName'] . "</td></tr>" .
                    "<tr><td class=\"left\">Grade: </td><td class=\"right\">" . $row['gradeLevel'] . "</td></tr>" .
                    "<tr><td class=\"left\">Teacher: </td><td class=\"right\">" . $row['teacherName'] . "</td></tr>" .
                    "<tr><td class=\"left\">Room: </td><td class=\"right\">" . $row['roomNum'] . "</td></tr>";
    }
    return $details;
}

//retrieves and returns info to display on grades page
function getCourseGrades() {
    //get session userId
    $id = $_SESSION['userId'];
    $query = "SELECT * FROM gradeBook WHERE userId = '$id'";
    $result = mysql_query($query) or die ('Error: ' . mysql_error());
    $grade = array();
    $percent = array();
    $classes = array();
    $allPts = array();
    $allStuPts = array();
    $assignments = array();
    $stuAssignPer = array();
    $stuAssignGrade = array();

    while($row = mysql_fetch_array($result)) {
        if(empty($classes)) {
            $classes[] = $row['subject'];
            $allPts[] = $row['totPts'];
            $allStuPts[] = $row['studentPts'];
        }
        else {
            if(in_array($row['subject'], $classes)) {
                $i = array_search($row['subject'], $classes);
                $value = $row['totPts'];
                $allPts[$i] += $value;
                $j = array_search($row['subject'], $classes);
                $stuValue = $row['studentPts'];
                $allStuPts[$j] += $stuValue;
            }
            else {
                $classes[] = $row['subject'];
                $allPts[] = $row['totPts'];
                $allStuPts[] = $row['studentPts'];
            }
        }
        if (empty($assignments)) {
            $assignments[$row['subject']] .= $row['assignment'] . ",";
            $sPts = $row['studentPts'];
            $tPts = $row['totPts'];
            $per = ($sPts / $tPts) * 100;
            $stuAssignPer[$row['subject']] .= $per . ",";
        }
        elseif (!array_key_exists($row['assignment'], $assignments)) {
            $assignments[$row['subject']] .= $row['assignment'] . ",";
            $sPts = $row['studentPts'];
            $tPts = $row['totPts'];
            $per = ($sPts / $tPts) * 100;
            $stuAssignPer[$row['subject']] .= $per . ",";
        }
        else {
            foreach($assignments as $key => $value) {
                if($key == $row['subject']) {
                    $value .= " " . $row['assignment'];
                }
            }
        }
    }

    for($i=0; $i<sizeof($allPts); $i++) {
        $sPts = $allStuPts[$i];
        $tPts = $allPts[$i];
        $classGrade = ($sPts / $tPts) * 100;
        if($classGrade >= 90) {
            $grade[$i] = "A";
        }
        else if($classGrade >= 80) {
            $grade[$i] = "B";
        }
        else if($classGrade >= 70) {
            $grade[$i] = "C";
        }
        else if($classGrade >= 60) {
            $grade[$i] = "D";
        }
        else {
            $grade[$i] = "F";
        }
        $percent[$i] = round($classGrade, 2);
    }

    for($i=0; $i<sizeof($classes); $i++) {
        $details .= "<tr class=\"open\"><td class=\"centerAll gradesLeft\"><a href=\"#\" onClick=\"displayInfo('" . $classes[$i] . "Assign')\">" . $classes[$i] . 
        "</a></td><td class=\"centerAll gradesMid\">" . $percent[$i] . "</td><td class=\"centerAll\">" . $grade[$i] . "</td></tr>";
        foreach($assignments as $key => $value) {
            if($classes[$i] == $key) {
                $classAssign = explode(',', $assignments[$key]);
                $stuPer = explode(',', $stuAssignPer[$key]);
                $stuGrade = array();
                for($h=0; $h<sizeof($stuPer); $h++) {
                    if($stuPer[$h] >= 90) {
                        $stuGrade[$h] = "A";
                    }
                    else if($stuPer[$h] >= 80) {
                        $stuGrade[$h] = "B";
                    }
                    else if($stuPer[$h] >= 70) {
                        $stuGrade[$h] = "C";
                    }
                    else if($stuPer[$h] >= 60) {
                        $stuGrade[$h] = "D";
                    }
                    else {
                        $stuGrade[$h] = "F";
                    }
                }
                $details .= "<tr class=\"hidden\" id=\"" . $key . "Assign\"><td class=\"centerAll gradesLeft\">";
                for($j=0; $j<sizeof($classAssign); $j++) {
                    if($classAssign[$j] != "") {
                        $details .=  $classAssign[$j] . "<br>";
                    }
                }
                $details .= "</td><td class=\"centerAll gradesMid\">";
                for($j=0; $j<sizeof($classAssign); $j++) {
                    if($classAssign[$j] != "") {
                        $details .=  $stuPer[$j] . "<br>";
                    }
                }
                $details .= "</td><td class=\"centerAll\">";
                for($j=0; $j<sizeof($classAssign); $j++) {
                    if($classAssign[$j] != "") {
                        $details .=  $stuGrade[$j] . "<br>";
                    }
                }
                $details .= "</td></tr>";
            }
        }
    }
    return $details;
}

//retrieves and returns info to display on schedule page
function getScheduleInfo() {
    //get session userId
    $id = $_SESSION['userId'];
    $gradebookQuery = "SELECT * FROM gradeBook WHERE userId = '$id'";
    $gradebookResult = mysql_query($gradebookQuery) or die ('Error: ' . mysql_error());
    $courses = array();

    while($row = mysql_fetch_array($gradebookResult)) {
        if (!array_key_exists($row['subject'], $courses)) {
            $courses[$row['subject']] = $row['courseLevel'];
        }
    }

    while($row = mysql_fetch_array($studentinfoResult)) {
        $teacher = $row['teacherName'];
        $room = $row['roomNum'];
    }

    $getTeacherInfo = getStudentInfo();
    $teacherInfo = explode(',', $getTeacherInfo);

    foreach ($courses as $key => $value) {
        $details .= "<tr><td class=\"centerAll\">" . $key . "</td><td class=\"centerAll\">" . $value . 
                    "</td><td class=\"centerAll\">" . $teacherInfo[0] . "</td><td class=\"centerAll\">" . $teacherInfo[1] . "</td></tr>";
    }
    return $details;
}

function getStudentInfo() {
    //get session userId
    $id = $_SESSION['userId'];
    $studentinfoQuery = "SELECT * FROM studentInfo WHERE userId = '$id'";
    $studentinfoResult = mysql_query($studentinfoQuery) or die ('Error: ' . mysql_error());

    while($row = mysql_fetch_array($studentinfoResult)) {
        $teacher = $row['teacherName'] . "," . $row['roomNum'];
    }
    return $teacher;
}

//retrieves and returns info to display on discipline page
function getDisciplineInfo() {
    //get session userId
    $id = $_SESSION['userId'];
    $query = "SELECT * FROM discipline WHERE userId = '$id'";
    $result = mysql_query($query) or die ('Error: ' . mysql_error());
    $attendence = array();
    $behavior = array();
    $numAttendRecords = "numAttendRecords";
    $attendComments = "attendComments";
    $numBehaviorRecords = "numBehaviorRecords";
    $behaviorComments = "behaviorComments";

    while($row = mysql_fetch_array($result)) {
        array_push($attendence, $row['attendComments']);
        array_push($behavior, $row['behaviorComments']);
    }

//get attendence comments
    $attendCnt = 0;
    foreach($attendence as $value) {
        if (!empty($value)) {
            $attendCnt++;
        }
    }

    $details = "<tr class=\"open\"><td class=\"disLeft\">Attendence Information:</td><td class=\"disRight\"><a href=\"#\" onClick=\"displayInfo('" 
               . $numAttendRecords . "')\">" . $attendCnt . "</a> records</td></tr>";
    if (sizeof($attendence) > 0) {
        for ($i=0; $i<sizeof($attendence); $i++) {
            if (!empty($attendence[$i])) {
                //$counter = 1;
                $details .= "<tr class=\"hidden\" id=" . $numAttendRecords . 
                            "><td class=\"disLeft\"> </td><td class=\"disRight\"><a href=\"#\" onClick=\"displayInfo('" 
                            . $attendComments . "')\">" . $attendCnt . "</a> Comment</td></tr>" . 
                            "<tr class=\"hidden\" id=" . $attendComments . "><td class=\"disLeft\">" . 
                            "</td><td class=\"disRight\">" . $attendence[$i] . "</td></tr>";
            }
        }
    }
    else {
        $details .= "<tr class=\"hidden\" id=" . $numAttendRecords . "><td class=\"disLeft\"></td><td><table><tr>" . 
                    "<td class=\"disRight\">There are no attendence comments" . "</td></tr></table></td></tr>";
    }

//get behavior comments
    $behaviorCnt = 0;
    foreach($behavior as $value) {
        if (!empty($value)) {
            $behaviorCnt++;
        }
    }

    $details .= "<tr class=\"open\"><td class=\"disLeft\">Behavior Information:</td><td class=\"disRight\"><a href=\"#\" onClick=\"displayInfo('" . $numBehaviorRecords . 
                "')\">" . $behaviorCnt . "</a> records</td></tr>";
    if (sizeof($behavior) > 0) {
        for ($i=0; $i<sizeof($behavior); $i++) {
            if (!empty($behavior[$i])) {
                $details .= "<tr class=\"hidden\" id=" . $numBehaviorRecords . 
                            "><td class=\"disLeft\"> </td><td class=\"disRight\"><a href=\"#\" onClick=\"displayInfo('" 
                            . $behaviorComments . "')\">" . $behaviorCnt . "</a> Comment</td></tr>" . 
                            "<tr class=\"hidden\" id=" . $behaviorComments . "><td class=\"disLeft\">" . 
                            "</td><td class=\"disRight\">" . $behavior[$i] . "</td></tr>";
            }
        }
    }
    else {
        $details .= "<tr class=\"hidden\" id=" . $numBehaviorRecords . "><td class\"disLeft\"></td>" . 
                    "<td class=\"disRight\">There are no behavior comments" . "</td></tr>";
    }


    return $details;
}
?>