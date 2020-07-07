<?php

// test shortcut assign operators complex

$a = 10;
$a+= 1;
println($a);
$a-= 2;
println($a);
$a*= 2;
println($a);
$a/= 2;
println($a);


// arrays
$A = array(100);
$i = 0;
$A[$i] += 1;
println($A[$i]);
$A[$i] -= 4;
println($A[$i]);
$A[$i] *= 2;
println($A[$i]);
$A[$i] /= 2;
println($A[$i]);

// function println($a) {
// print $a;
// print "\n";
// }

?>