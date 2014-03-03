xhttp=new XMLHttpRequest();
xhttp.open("GET","cv.xml",false);
xhttp.send();
xmlDoc=xhttp.responseXML;

function start()
{
    insertPageHeader();
    insertDegreesContent();
    insertEmploymentContent();
    insertPublicationContent();
    insertFundingContent();
}

//Generate Page Header Content
function insertPageHeader()
{
    var div = document.getElementById("doctitle");
    var node;
    var h1 = document.createElement("h1");
    h1.setAttribute("id", "doctitle");
    node = document.createTextNode(xmlDoc.getElementsByTagName("DOCTITLE")[0].childNodes[0].nodeValue);
	h1.appendChild(node);
	div.appendChild(h1);
	var h3 = document.createElement("h3");
	h3.setAttribute("id", "subtitle");
    node = document.createTextNode(xmlDoc.getElementsByTagName("TYPEOFDOC")[0].childNodes[0].nodeValue);
    h3.appendChild(node);
    div.appendChild(h3);
}

//Generate Degrees Content
function insertDegreesContent()
{
    var degrees = xmlDoc.getElementsByTagName("DEGREE");

    for (i = 0; i < degrees.length; i++)
    {
        var div = document.getElementById("degrees");
        var node;
        var institute = degrees[i].getElementsByTagName("INSTITUTE");
    
        var p1 = document.createElement("p");
        p1.className = "firstLine";
        node = document.createTextNode(degrees[i].getAttribute("YEAR") + " ");
        p1.appendChild(node);
        node = document.createTextNode(degrees[i].getElementsByTagName("TYPE")[0].childNodes[0].nodeValue + " ");
        p1.appendChild(node);
        node = document.createTextNode(degrees[i].getElementsByTagName("MAJOR")[0].childNodes[0].nodeValue + " ");
        p1.appendChild(node);
        div.appendChild(p1);
    
        var pS = document.createElement("p");
        pS.className = "subLine";
        for (j = 0; j < institute.length; j++)
        {
            node = document.createTextNode(institute[j].getElementsByTagName("NAME")[0].childNodes[0].nodeValue + "; ");
            pS.appendChild(node);
            node = document.createTextNode(institute[j].getAttribute("ADDRESS"));
            pS.appendChild(node);
            div.appendChild(pS);
        }
    }
}

//List Degrees
function displayDegrees()
{
    if (document.getElementById("degrees").className == "hidden")
    {
        document.getElementById("degrees").className = "open";
        document.getElementById("degreeBtn").setAttribute("value", "Hide Details");
    }
    else
    {
        document.getElementById('degrees').className = "hidden";
        document.getElementById("degreeBtn").setAttribute("value", "Show Details");
    }
}

//Generate Employment History Content
function insertEmploymentContent()
{
    var employments = xmlDoc.getElementsByTagName("EMPLOYMENT");

    for (i = 0; i < employments.length; i++)
    {
        var div = document.getElementById("employment");
        var node;
        var h3 = document.createElement("h3");
        var p = document.createElement("p");
        p.className = "emadd";
        
        var employer = employments[i].getElementsByTagName("EMPLOYER");
    
        node = document.createTextNode(employer[0].getElementsByTagName("NAME")[0].childNodes[0].nodeValue);
        h3.appendChild(node);
        div.appendChild(h3);
        node = document.createTextNode(employer[0].getAttribute("ADDRESS"));
        p.appendChild(node);
        div.appendChild(p);
        
        var jobs = employments[i].getElementsByTagName("JOB");
        
        for (j = 0; j < jobs.length; j++)
        {
            var tab = document.createElement("table");
            var tr1 = document.createElement("tr");
            var tr2 = document.createElement("tr");
            var td1 = document.createElement("td");
            var td2 = document.createElement("td");
            var td3 = document.createElement("td");
            td1.className = "left";
            td2.className = "firstLine";
            
            node = document.createTextNode(jobs[j].getAttribute("BEGINMONTH") + " ");
			td1.appendChild(node);
			node = document.createTextNode(jobs[j].getAttribute("BEGINYEAR") + " - ");
			td1.appendChild(node);
			node = document.createTextNode(jobs[j].getAttribute("ENDDATE"));
			td1.appendChild(node);
			tr1.appendChild(td1);
			node = document.createTextNode(jobs[j].getElementsByTagName("TITLE")[0].childNodes[0].nodeValue);
			td2.appendChild(node);
			tr1.appendChild(td2);
			tab.appendChild(tr1);
			tr2.appendChild(td1.cloneNode(false));
			node = document.createTextNode(jobs[j].getElementsByTagName("RESPONSIBILITIES")[0].childNodes[0].nodeValue);
			td3.appendChild(node);
			tr2.appendChild(td3);
			tab.appendChild(tr2);
			div.appendChild(tab);
        }
    }
}

//List Employment History
function displayEmployment()
{
    if (document.getElementById("employment").className == "hidden")
    {
        document.getElementById("employment").className = "open";
        document.getElementById("employmentBtn").setAttribute("value", "Hide Details");
    }
    else
    {
        document.getElementById('employment').className = "hidden";
        document.getElementById("employmentBtn").setAttribute("value", "Show Details");
    }
}

//Generate Publication Record Content
function insertPublicationContent()
{
    var publications = xmlDoc.getElementsByTagName("PUBLICATION");

    for (i = 0; i < publications.length; i++)
    {
        var div = document.getElementById("publications");
        var node;
        var authors = publications[i].getElementsByTagName("AUTHOR");
        var x = authors.length - 1;
    
        var p1 = document.createElement("p");
        p1.className = "firstLine";
    
        for (j = 0; j < authors.length; j++)
        {
            if (j < x)
            {
                node = document.createTextNode(authors[j].childNodes[0].nodeValue + ", ");
                p1.appendChild(node);
            }
            else 
            {
                node = document.createTextNode(authors[j].childNodes[0].nodeValue + ". ");
                p1.appendChild(node);
            }
        }
        
        div.appendChild(p1);
    
        var pS = document.createElement("p");
        pS.className = "subLine";
        
        node = document.createTextNode(publications[i].getElementsByTagName("TITLE")[0].childNodes[0].nodeValue + ". ");
        pS.appendChild(node);
        
        if (publications[i].getAttribute("YEAR") != null)
        {
            node = document.createTextNode(publications[i].getAttribute("YEAR") + ". ");
            pS.appendChild(node);
        }
    
        node = document.createTextNode(publications[i].getElementsByTagName("PUBLISHER")[0].childNodes[0].nodeValue + ". ");
        pS.appendChild(node);
    
        if (publications[i].getAttribute("PAGES") != null)
        {
            node = document.createTextNode(publications[i].getAttribute("PAGES") + ". ");
            pS.appendChild(node);
        }
    
        if (publications[i].getAttribute("VOLUME") != null)
        {
            node = document.createTextNode(publications[i].getAttribute("VOLUME") + ". ");
            pS.appendChild(node);
        }
        
        div.appendChild(pS);
    }
}

//List Publication Record
function displayPublications()
{
    if (document.getElementById("publications").className == "hidden")
    {
        document.getElementById("publications").className = "open";
        document.getElementById("publicationsBtn").setAttribute("value", "Hide Details");
    }
    else
    {
        document.getElementById('publications').className = "hidden";
        document.getElementById("publicationsBtn").setAttribute("value", "Show Details");
    }
}

//Generate Funding Record Content
function insertFundingContent()
{
    var projects = xmlDoc.getElementsByTagName("PROJECT");

    for (i = 0; i < projects.length; i++)
    {
        var div = document.getElementById("funding");
        var node;
        var sponsors = projects[i].getElementsByTagName("SPONSOR");
   
        var p1 = document.createElement("p");
        p1.className = "firstLine";
        node = document.createTextNode(projects[i].getAttribute("BEGINYEAR") + "    ");
        p1.appendChild(node);
        node = document.createTextNode(projects[i].getElementsByTagName("NAME")[0].childNodes[0].nodeValue);
        p1.appendChild(node);
        div.appendChild(p1);
    
        var pS = document.createElement("p");
        pS.className = "subLine";
        node = document.createTextNode(projects[i].getElementsByTagName("SPONSOR")[0].childNodes[0].nodeValue + ", ");
        pS.appendChild(node);
        node = document.createTextNode(sponsors[0].getAttribute("AMOUNT"));
        pS.appendChild(node);
        div.appendChild(pS);
        
        var pS2 = document.createElement("p");
        pS2.className = "subLine";
        node = document.createTextNode(projects[i].getElementsByTagName("ROLE")[0].childNodes[0].nodeValue);
        pS2.appendChild(node);
        div.appendChild(pS2);
    }
}

//List Funding Record
function displayFundingRecord()
{
    if (document.getElementById("funding").className == "hidden")
    {
        document.getElementById("funding").className = "open";
        document.getElementById("fundingBtn").setAttribute("value", "Hide Details");
    }
    else
    {
        document.getElementById('funding').className = "hidden";
        document.getElementById("fundingBtn").setAttribute("value", "Show Details");
    }
}

//List Questions
function displayQuestions()
{
    if (document.getElementById("questions").className == "hidden")
    {
        document.getElementById("questions").className = "open";
        document.getElementById("questionsBtn").setAttribute("value", "Hide Details");
    }
    else
    {
        document.getElementById('questions').className = "hidden";
        document.getElementById("questionsBtn").setAttribute("value", "Show Details");
    }
}