<?php

// return array from function

$A = $B = $C = array(1);
$A[0] = 10;
println($A[0]);
$A = foo($A);
println($A[0]);

function foo($A) {
$A[0] = 20;
return $A;
}

// function println($a) {
// print $a;
// print "\n";
// }


?>