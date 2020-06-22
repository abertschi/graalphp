<?php
// nested function invocations

function foo($a, $b, $c, $d, $e, $f) {
    return $a * 50 + square($b) * square(square(square($c))) - $e + $f;
    
}

function square($val) {
    return $val* $val;
}

// print foo(1, 2, 3, 4, 5, 6);
print foo(1, 2, 3, 4, 5, 6);
?>
