<?php
// correctly pass array by value not by reference even if we dont assign result into variable


function test1() {
$A = array(array(1));

print($A[0][0]);
foo($A)[0][0] = 100;
println($A[0][0]);
}

function foo(&$A) {
return $A;
}

test1();


// --------------
// return by reference, dont assign and change directly

function test2() {
$B = array(array(2));

print($B[0][0]);
too($B)[0][0] = 200;
println($B[0][0]);
}

function &too(&$B) {
return $B;
}


test2();



// --------------
// return by reference but assign by value

function test3() {
$C = array(array(3));

print($C[0][0]);
$D = boo($C); // this must be copied
$C[0][0] = 4;
print($C[0][0]); //4
println($D[0][0]); //3 !
}

function &boo(&$C) {
return $C;
}

test3();

// --------------
// return by reference and  assign by reference

function test4() {
$D = array(array(3));

print($D[0][0]);
$E = &koo($D); // this must be referenced
$D[0][0] = 4;
print($E[0][0]); // 4
println($D[0][0]); //4
}

function &koo(&$C) {
return $C;
}

test4();

// function println($a) {
// echo $a ."\n";
// }
?>
