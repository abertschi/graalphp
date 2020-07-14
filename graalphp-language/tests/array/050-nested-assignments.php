<?php
// test correct copy by value behavior of nested arrays

$A = array(1, 2, 3); // long
$B = array($A, 100, 200.2, 300); // mixed
$C = array($B, 10, 20); //mixed

println($C[1]);
println($C[2]);
println($C[0][1]);
println($C[0][2]);

println($C[0][0][0]);
println($C[0][0][1]);
println($C[0][0][2]);


foo($C);


println($C[1]);
println($C[2]);
println($C[0][1]); // here we get 101 from foo, but should be 100
println($C[0][2]);

println($C[0][0][0]); // here we get 1.1 but should be 1
println($C[0][0][1]);
println($C[0][0][2]);



function foo($A) {
$A[1] = 11;
$A[0][1] = 101;
$A[0][0][0] = 1.1;

println($A[1]);
println($A[2]);
println($A[0][1]);
println($A[0][2]);

println($A[0][0][0]);
println($A[0][0][1]);
println($A[0][0][2]);
}

// function println($a) {
// echo $a . "\n";
// }
?>