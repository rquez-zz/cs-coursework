<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>Project Four</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">
    </head>
    <body>
        <div class="container">
            <div class="row">
                <h2 class="text-center">CNT4714 - Project Four Database Client</h2>
            </div>
            <hr>
            <div class="row">
                <div class="col-md-4">
                    <form action="query.php" method="post">
                        <div class="form-group">
                            <label for="username">Username</label>
                            <input type="text" class="form-control" id="username" name="username" >
                        </div>
                        <div class="form-group">
                            <label for="password">Password</label>
                            <input type="password" class="form-control" id="password" name="password">
                        </div>
                        <div class="form-group">
                            <button type="submit" class="btn btn-default">Login</button>
                        </div>
                    </form>
                </div>
                <div class="col-md-8">
                    <h4>Welcome to the Database Client</h4>
                    <p>
                        This system will allow any registered user to run SQL queries and update statements
                        on the database shown below, once login to the system is successful.
                    </p>
                    <h4>Database Connection:</h4>
                    <p>
                        Connection to the MySQL Database named: localhost:3310/project4
                    </p>
                    <h4>System Features</h4>
                    <ul>
                        <li>User Authentication</li>
                        <li>Select query</li>
                        <li>Update query</li>
                        <li>Business Logic Implementation</li>
                        <li>Results Page</li>
                        <li>Error Checking</li>
                    </ul>
                    <h4>User Login</h4>
                    <p>
                        Client login to the system uses the login area on the upper left.
                    </p>
                    <ul>
                        <li><strong>Username:</strong>Admin</li>
                        <li><strong>Password:</strong>Pass</li>
                    </ul>
                </div>
            </div>
        </div>
    </body>
</html>
