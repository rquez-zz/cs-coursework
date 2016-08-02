<?php
    session_start();

    $username = $_SESSION['username'];
    $password = $_SESSION['password'];
    $host = $_SESSION['host'];
    $database = $_SESSION['database'];
    $query = $_POST['query'];

    $defaultQuery = 'select * from suppliers';
    $bizQuery = "update suppliers set status = status + 5 where snum in(select distinct snum from shipments where quantity >= 100)";

    $connection = @mysqli_connect($host, $username, $password, $database);

    if (!$connection) {
        $_SESSION['error'] = 'Error connecting to database.';
        header("Location: error.php");
    }

    if (preg_match_all("/(update shipments|insert into shipments)/i", $query) == 1 && !($user == 'client1')) {

        if(!mysqli_query($connection, $query)) {
            $_SESSION['error'] = mysqli_error($connection);
            header("Location: error.php");
        }


        if(!mysqli_query($connection, $bizQuery)) {
            $_SESSION['error'] = mysqli_error($connection);
            header("Location: error.php");
        }

        $bizString = "Business Logic Modification To Suppliers Table - Results";

        if (mysqli_affected_rows($connection) > 0) {

            if(!$result = mysqli_query($connection, $defaultQuery)) {
                $_SESSION['error'] = mysqli_error($connection);
                header("Location: error.php");
            }

            $alert = 'ALERT: SUPPLIER STATUS HAS CHANGED DUE TO BUSINESS LOGIC. DISPLAYING UPDATED SUPPLIER TABLE!';
            echo '<div class="alert alert-warning">' . $alert . '</div>';
        }

    } else {

        if (empty($query)) {
            $result = mysqli_query($connection, $defaultQuery);
        } else {
            if(!$result = mysqli_query($connection, $query)) {
                $_SESSION['error'] = mysqli_error($connection);
                header("Location: error.php");
            }
        }
    }

    if(!($metadata = mysqli_fetch_fields($result))) {
        $rowsAffected = mysqli_affected_rows($db);
    }

    mysqli_close($connection);
?>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>Query Result</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">
    </head>
    <body>
        <div class="container">
            <div class="row">
                <h2 class="text-center">CNT4714 - Project Four Database Client</h2>
            </div>

            <div class="row">
                <div class="col-md-4">
                    <h4>Welcome back</h4>
                    <p><?php echo $user ?></p>
                    <form action="index.php" method="get" class="logoutForm">
                        <input type="submit" name="logout" value="Logout" class="btn btn-default">
                    </form>
                    <a href="query.php">
                        <input value="New Query" type="button" class="btn btn-default">
                    </a>
                </div>
                <div class="col-md-8">
                    <?php
                        if(isset($bizString)) {
                            echo "<p><strong>$bizString</strong></p>";
                        }
                        if (isset($rowsAffected)){
                            echo "<h4>The statement executed successfully. $rowsAffected row(s) affected.</h4>";
                        } else {
                            print("<table class='table'><thead><tr>");
                            for ( $i = 0; $i < count($metadata); $i++) {
                                print("<th>");
                                print($metadata[$i]->name);
                                print("</th>");
                            }
                            print("</tr></thead>");
                            print("<tbody>");
                            while($row = mysqli_fetch_row($result)) {
                                print("<tr>");
                                foreach ($row as $key => $value) {
                                    print("<td>".$value."</td>");
                                }
                                print("</tr>");
                            }
                            print("</tbody></table>");
                        }
                    ?>
                </div>
                <div>
                    <a href="index.php">Return to Main Page</a>
                </div>
            </div>
        </div>
    </body>
</html>
