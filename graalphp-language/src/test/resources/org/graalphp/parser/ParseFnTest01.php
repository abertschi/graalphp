<?php

function foo($b, $a = $a + 1) {
    $a = 1;
    return 1337;
}

foo();
?>