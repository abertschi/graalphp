<?php
echo "running file";

$a = 1;

function f() {
    // global $a;
    $b = 1;
    

    function f2() {
        echo $b;
    }

        f2();
}


function t1() {
    $a = 0;

    if (true)
    {
        $b = 2;
        echo $a;

     }
    echo $b;
}

t1();
?>
