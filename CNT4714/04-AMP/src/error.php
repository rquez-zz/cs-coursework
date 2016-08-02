<?php
  session_start();
  $error = $_SESSION['error'];
?>

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
                <h1>Major Error:</h1>
                <p>An SQL Exception occured while interacting with the Project4 database.</p>
                <p>The error message was:
                    <br>
                    <strong><?php echo $error; ?></strong>
                </p>
                <p>Please try again later</p>
                <a href="index.php">
                  <button class="btn btn-default">Home</button>
                </a>
                <a href="query.php">
                  <button class="btn btn-default">New Query</button>
                </a>
            </div>
        </div>
    </body>
</html>
