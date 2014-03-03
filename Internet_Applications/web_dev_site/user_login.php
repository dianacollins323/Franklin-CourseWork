<html lang="en">
    <head>
           <title>Login</title>
           <link href="css/login.css" rel="stylesheet">
    </head>
    <body>
        <div id="page-content-wrapper">
            <h1>CCS Parents Portal</h1>
            <form method="post" action="php/validate_user.php" id="login" name="loginForm">
                <h3 id="formHeader">Please sign in</h3>
                    <div class="form">
                        <label for:"User Name">
                            User Name
                        </label>
                        <input name="user" type="text" required/> 
                    </div>
                    <div class="form">
                        <label for:"Password">
                            Password
                        </label>
                        <input name="password" type="password" required/> 
                    </div>
                    <div class="form">
                        <input type="submit" id="submit" name="submit" value="Submit" /> 
                    </div>
                </div>
            </form>
        </div>
    </body>
</html>