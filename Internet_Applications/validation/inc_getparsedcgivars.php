<?php
	function get_user_browser() {
	$browser_list = array(
        'Internet Explorer' => 'MSIE',
        'Google Chrome' => 'Chrome',
        'Firefox' => 'Firefox',
        'Opera' => 'Opera',
        'Safari' => 'Safari'
	);

	foreach($browser_list as $ub=>$Match) {
        if(eregi($Match, $_server[HTTP_USER_AGENT])) {
        	break;
        }
        else {
        	$ub = 'browser not on list';
        }
	}

	echo 'Your browser: ' . $ub . '<br>';
	return $ub;
}

function get_user_os() {
	$os_list = array(
        'Windows 3.11' => 'Win16',
        'Windows 95' => '(Windows 95) | (Win95) | (Windows_95)',
        'Windows 98' => '(Windows 98) | (Win98)',
        'Windows 2000' => '(Windows NT 5.0) | (Windows 2000)',
        'Windows XP' => '(Windows NT 5.1) | (Windows XP)',
        'Windows Server 2003' => '(Windows NT 5.2)',
        'Windows Vista' => '(Windows NT 6.0)',
        'Windows 7' => '(Windows NT 7) | (Windows NT 6.1)',
        'Windows NT 4' => '(Windows NT 4.0) | (WinNT4.0) | (WinNT) | (Windows NT)',
        'Windows ME' => 'Windows ME',
        'Open BSD' => 'OpenBSD',
        'Sun OS' => 'SunOS',
        'Linux' => '(Linux) | (X11)',
        'Mac OS' => '(Mac_PowerPC) | (Macintosh)',
        'QNX' => 'QNX',
        'BeOS' => 'BeOS',
        'OS/2' => 'OS/2',
        'Search Bot' => '(nuhk) | (Googlebot) | (Yammybot) | (Openbot) | (Slurp) | (MSNBot) | (Ask Jeeves/Teoma)',
        'OS X' => 'Mac OS X'
	);

	foreach($os_list as $os=>$Match) {
		if(eregi($Match, $_server[HTTP_USER_AGENT])) {
        	break;
        }
        else {
        	$os = 'os not on list';
        }
	}

	echo 'Your os: ' . $os . '<br>';
	return $os;
}
?>