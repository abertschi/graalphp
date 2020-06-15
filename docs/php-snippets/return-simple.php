<?php

function foo($b, $a = $b + 1) {
    $a = 1;
    return 1337;
}

foo();
?>
