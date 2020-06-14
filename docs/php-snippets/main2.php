<?php
    
function main() {
$_argv = array("main.php", 1, "a");
$_argc = 3;


$test = function() use (&$_argv, &$_argc) {
    echo "output: ";
    echo $_argc;
};

$test();

}


main();
?>
