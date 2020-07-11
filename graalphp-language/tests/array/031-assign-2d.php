<?php

// assign 2d array by value
$A = $B = $C = array(1);

$A[0] = 10;
$B[0] = 20;

$A[0] = $B;
$B[0] = 123;

println($A[0][0]);
println($B[0]);


// function println($a) {
// print $a;
// print "\n";
// }





?>