<?php

// class X {
// public $a;
// public $b;
// };
//
// $X = new X();
// $X->a = 1;
// $X->b = 2;

$X = 1;
$A = array(1, 1.5, $X);
$B = array($A);

println($B[0][0]);
// var_dump($B);
foo($B);
println($B[0][0]);
// var_dump($B);

function foo($arr) {
$arr[0][1] = 2;
// $arr[0][2]->a = 2;
// var_dump($arr);
}











?>