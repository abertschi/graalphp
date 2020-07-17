<?php

function foo() {
    $A = array(array(array(array(array(100)))));
    return $A;
}
$A = foo();
$B = $C = $D = $A;
$a = 0;

$a += $A[0][0][0][0][0];
$a += $B[0][0][0][0][0];
$a += $C[0][0][0][0][0];
$a += $D[0][0][0][0][0];

print($a);





?>
