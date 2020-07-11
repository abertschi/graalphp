<?php
// test array(...) with initial values, more complex

$B = array(1.1);
$C = array(array(1.2));
$A = array(3, $B, $C);
print($A[0]);
print($A[1][0]);
print($A[2][0][0]);
?>