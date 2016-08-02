<?php
    session_start();

    $host = "localhost";
    $database = "project4";

    if(isset($_POST['username']) && isset($_POST['password'])) {
        $username = $_POST['username'];
        $password = $_POST['password'];

        $connection = @mysqli_connect($host, $username, $password, $database);

        if (!$connection) {
            $_SESSION['error'] = 'Error connecting to database.';
            header("Location: error.php");
        }

        mysqli_close($connection);

        $_SESSION['database'] = $database;
        $_SESSION['host'] = $host;
        $_SESSION['username'] = $username;
        $_SESSION['password'] = $password;

    } else {
        $username = $_SESSION['user'];
    }
?>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>Enter Query</title>
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
                <h4>Welcome back</h4>
                <p><?php echo $username ?></p>
                <form action="index.php" method="get" class="logoutForm">
                    <button type="submit" name="logout" value="true" class="btn btn-default">Logout</button>
                </form>
            </div>
            <div class="col-md-8">
                <h4>Enter Query</h4>
                <p>
                Please enter a valid SQL query or update statement. You may also just press "Submit Query"
                to run a default query against the database.
                </p>
                <form action="result.php" method="post">
                    <textarea id="query" name="query" rows="8" cols="40" class="form-control"></textarea>
                    <div class="form-inline" id="queryButtons">
                        <input type="submit" value="Submit Query" class="btn btn-primary">
                        <input type="submit" value="Submit Update"class="btn btn-primary">
                        <input type="button" value="Reset Window" name="reset" class="btn btn-primary" onclick="document.getElementById('query').value = '';">
                    </div>
                </form>
            </div>
        </div>
    </body>
</html>
