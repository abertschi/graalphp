<?php
    
function main() {
$_argv = array("main.php", 1, "a");
$_argc = 3;


function test() {
    global $_argv;
    global $_argc;
    echo "output: ";
    echo $_argc;
}

test();

}


main();
?>
