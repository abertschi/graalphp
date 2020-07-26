<?php
// test array fill

// int, start 0
$A = array_fill(0, 100, 10);
for($i = 0;  $i < 100; $i ++) {
print($A[$i]);
}


// int start != 0
$B = array_fill(10, 100, 10);
for($i = 10;  $i < 100; $i ++) {
print($A[$i]);
}


// double start 0
$A = array_fill(0, 100, 10.1);
for($i = 0;  $i < 100; $i ++) {
print($A[$i]);
}


// double start 0
$A = array_fill(0, 100, 10.1);
for($i = 0;  $i < 100; $i ++) {
print($A[$i]);
}


// array, start 0
$A0 = array(1, 2,3);
$A = array_fill(0, 100, $A0);
for($i = 0;  $i < 100; $i ++) {
print($A[$i][0]);
}

// array, start !=0
$A0 = array(1, 2,3);
$A = array_fill(0, 100, $A0);
for($i = 50;  $i < 100; $i ++) {
print($A[$i][0]);
}

?>