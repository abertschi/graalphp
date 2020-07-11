<?php

// simple assigns
$A = $B = $C = array(1);

$A[0] = 10;
$B[0] = 20;
$C[0] = 30;

// array assigns
$D = $A;
$D[1] = 50;
$D[0] = 60;

println($D[0]);
println($D[1]);

println($A[0]);
println($B[0]);
println($C[0]);




// function println($a) {
// print $a;
// print "\n";
// }





?>