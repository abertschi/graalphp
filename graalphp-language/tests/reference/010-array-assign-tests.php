<?php

// test assign by ref of nested arrays
function t1() {
$A = array(100);
$B = array(10, $A);

$C = & $B[1];

print($B[1][0]);
print($C[0]);
$C[0] = 12;
$D = $C; // this forces a copy
$D[0] = 13;

print($C[0]);
print($D[0]);
println($B[1][0]);




}

// function println($A) {
// echo $A . "\n";
// }



// test assign by ref of nested arrays
function t2() {
$A = array(100);
$B = array(10, $A);

$C = & $B[1];

print($B[1][0]);
print($C[0]);
$C[0] = 12;
$D =& $C; // copy by ref
$D[0] = 13;

print($C[0]);
print($D[0]);
print($B[1][0]);




}

t1();
t2();
?>