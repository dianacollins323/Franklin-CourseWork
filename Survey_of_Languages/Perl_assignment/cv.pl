use strict;
use XML::LibXML;    #import module to parse XML file

my $html_file = 'cv.html';                    #specify html file to write to
my $xml_file = 'cv.xml';                      #specify xml file to parse
my $parser = XML::LibXML->new();              #create parser
my $tree = $parser->parse_file($xml_file);    #parse file
my $root = $tree->getDocumentElement;         #assign a root variable

#create and open html file, cv.html, to write to
open HTML_FILE, ">", $html_file || die ("Could not open HTML file for writing!");

my $title = $root->findvalue('DOCINFO/DOCTITLE');
my $subtitle = $root->findvalue('DOCINFO/TYPEOFDOC');

#begin printing to html file
print HTML_FILE qq(<!DOCTYPE html>);
print HTML_FILE qq(<html>\n<head>\n);
print HTML_FILE qq(<title>$title</title>\n);
print HTML_FILE qq(<link rel="stylesheet" type="text/css" href="cv.css" />\n);
print HTML_FILE qq(</head>\n<body>\n<div id="main_container">\n);
print HTML_FILE qq(<div class="sections">\n);
print HTML_FILE qq(<h1 id="doctitle">$title</h1>\n);
print HTML_FILE qq(<h3 id="subtitle">$subtitle</h3>\n);
print HTML_FILE qq(</div>\n<div class="sections">\n);
print HTML_FILE qq(<h2 class="secHeader">\nAcademic Degrees\n</h2>\n);

#print each academic degree, major, and issuing institute
	foreach my $acdegree ($root->findnodes('ACADEMICDEGREES/DEGREE')) {
	    my $year = $acdegree->findvalue('@YEAR');
		my $type = $acdegree->findvalue('TYPE');
		my $maj = $acdegree->findvalue('MAJOR');
		my $name = $acdegree->findvalue('INSTITUTE/NAME');
	    my $address = $acdegree->findvalue('INSTITUTE/@ADDRESS');
		print HTML_FILE qq(<p class="firstLine">\n$year $type $maj\n</p>\n);
		print HTML_FILE qq(<p class="subLine">\n$name; $address\n</p>\n);
	}

print HTML_FILE qq(</div>\n<div class="sections">\n);
print HTML_FILE qq(<h2 class="secHeader">\nEmployment History\n</h2>\n);

#print each employer
    foreach my $job ($root->findnodes('EMPLOYMENTHISTORY/EMPLOYMENT')) {
        my $emName = $job->findvalue('EMPLOYER/NAME');
        my $emAdd = $job->findvalue('EMPLOYER/@ADDRESS');
        print HTML_FILE qq(<h3>\n$emName</h3>\n);
        print HTML_FILE qq(<p class="emadd">\n$emAdd</p>\n);
        print HTML_FILE qq(<table id="jobs">\n);
        
        #print each job, begin and end dates
        foreach my $position ($job->findnodes('JOB')) {
            my $beMo = $position->findvalue('@BEGINMONTH');
            my $beYear = $position->findvalue('@BEGINYEAR');
            my $end = $position->findvalue('@ENDDATE');
            my $title = $position->findvalue('TITLE');
            my $resp = $position->findvalue('RESPONSIBILITIES');
            print HTML_FILE qq(<tr>\n);
            print HTML_FILE qq(<td class="left">\n$beMo $beYear - $end\n</td>\n);
            print HTML_FILE qq(<td class="firstLine">\n$title\n</td>\n);
            print HTML_FILE qq(</tr>\n);
            print HTML_FILE qq(<tr>\n);
            print HTML_FILE qq(<td class="left">\n&nbsp;</td>\n);
            print HTML_FILE qq(<td>\n$resp\n</td>\n);
            print HTML_FILE qq(</tr>\n);
        }
        
        print HTML_FILE qq(</table>\n);
    }

print HTML_FILE qq(</div>\n<div class="sections">\n);
print HTML_FILE qq(<h2 class="secHeader">\nFunded Research\n</h2>\n);
print HTML_FILE qq(<table id="jobs">\n);

#print each research project, sponsor, and role
    foreach my $fr ($root->findnodes('FUNDINGRECORD/PROJECT')) {
        my $beMo = $fr->findvalue('@BEGINMONTH');
        my $beyr = $fr->findvalue('@BEGINYEAR');
        my $proj = $fr->findvalue('NAME');
        my $sponsor = $fr->findvalue('SPONSOR');
        my $amt = $fr->findvalue('SPONSOR/@AMOUNT');
        my $role = $fr->findvalue('ROLE');
        print HTML_FILE qq(<tr>\n);
		print HTML_FILE qq(<td class="left">\n$beMo $beyr\n</td>\n);
		print HTML_FILE qq(<td class="firstLine">\n$proj\n</td>\n);
		print HTML_FILE qq(</tr>\n);
		print HTML_FILE qq(<tr>\n);
		print HTML_FILE qq(<td class="left">\n&nbsp;</td>\n);
		print HTML_FILE qq(<td>\n$sponsor, $amt\n</td>\n);
		print HTML_FILE qq(</tr>\n);
		print HTML_FILE qq(<tr>\n);
		print HTML_FILE qq(<td class="left">\n&nbsp;</td>\n);
		print HTML_FILE qq(<td>\n$role\n</td>\n);
		print HTML_FILE qq(</tr>\n);
    }

print HTML_FILE qq(</table>\n);
print HTML_FILE qq(</div>\n<div class="sections">\n);
print HTML_FILE qq(<h2 class="secHeader">\nPublications\n</h2>\n);
print HTML_FILE qq(<ul>\n);

#print each publication
    foreach my $pub ($root->findnodes('PUBLICATIONRECORD/PUBLICATION')) {
         my $title = $pub->findvalue('TITLE');
         my $publisher = $pub->findvalue('PUBLISHER');
         print HTML_FILE qq(<li>\n);
         
         #print each author of the publication
         foreach my $author ($pub->findnodes('AUTHORS/AUTHOR')) {
             print HTML_FILE qq($author, );
         }
         
         print HTML_FILE qq($title. In $publisher);
         
         #if the attribute exists print its value otherwise do nothing
         if ($pub->hasAttribute('NUMBER')) {
             my $num = $pub->findvalue('@NUMBER');
             print HTML_FILE qq(, $num);
         }
         elsif ($pub->hasAttribute('VOLUME')) {
             my $vol = $pub->findvalue('@VOLUME');
             print HTML_FILE qq(, $vol);
         }
         elsif ($pub->hasAttribute('PAGES')) {
             my $pg = $pub->findvalue('@PAGES');
             print HTML_FILE qq(, $pg);
         }
         elsif ($pub->hasAttribute('YEAR')) {
             my $yr = $pub->findvalue('@YEAR');
             print HTML_FILE qq(, $yr);
         }
         
         print HTML_FILE qq(.\n</li>\n);
    }

print HTML_FILE qq(</ul>\n);
print HTML_FILE qq(</div>\n<div class="sections">\n);
print HTML_FILE qq(<h2 class="secHeader">\nQuestions\n</h2>\n);
print HTML_FILE qq(<p class="firstLine">\n);
#print questions and answers
print HTML_FILE qq(How does the Perl mechanism(s) for printing output compare in terms of
    ease-of-use, flexibility, power, or other criteria to the mechanism in another language
    you are familiar with?);
print HTML_FILE qq(</p>\n);
print HTML_FILE qq(<p>\n);
print HTML_FILE qq(Compared to JavaScript and XSLT, Perl is much easier to work with in
    regards to writing to an html file. There is no need to create an element, as there is
    in JavaScript, you simpily specify the file to print to and quote the tag. Because it
    is easier to use, especilly with the XML::LibXML module, fewer lines of code are required.);
print HTML_FILE qq(</p>\n);
print HTML_FILE qq(<p class="firstLine">\n);
print HTML_FILE qq(Choose a Perl feature (other than print features) that you used or one
    that looked intriguing but you were not able to find a use for. Describe that feature
    briefly and discuss how it is an improvement over the corresponding feature in another
    language that you know.);
print HTML_FILE qq(</p>\n);
print HTML_FILE qq(<p>\n);
print HTML_FILE qq(Perl has a feature called pragma. A pragma is a module that can be used
    to affect the program in some way. You can use modules that already exist or create 
    one. I used the strict pragma which forced me to declare all of my variables. It also 
    help with error detection early on which in turn help the programmer to develop better
    code.);
print HTML_FILE qq(</p>\n);
print HTML_FILE qq(</div>\n);
print HTML_FILE qq(</div>\n</body>\n</html>);

#close html file, cv.html
close(HTML_FILE);

#print html file name to screen
print $html_file;