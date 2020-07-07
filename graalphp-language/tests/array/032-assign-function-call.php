<?php

// copy by value behavior for function calls

$A = $B = $C = array(1);
$A[0] = 10;
println($A[0]);
foo($A);
println($A[0]);

function foo($A) {
$A[0] = 20;

}

// function println($a) {
// print $a;
// print "\n";
// }


?>