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



<?php
$foo = 1337;

    function test() {
        global $foo;
        echo "test: ";
        echo $foo;
    }

    test();
?>



<?php
function main() {
    $foo = 1337;

    function test() {
        global $foo;
        echo "test: ";
        echo $foo;
    }

        test();
}

main() // call main with truffle

?>
