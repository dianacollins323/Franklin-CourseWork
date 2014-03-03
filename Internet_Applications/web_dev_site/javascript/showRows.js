function displayInfo(id) {
    if (document.getElementById(id).className == "hidden")
    {
        document.getElementById(id).className = "open";
    }
    else
    {
        document.getElementById(id).className = "hidden";
    }
}