<?php

$n = 30;
$A = array();
for($i = 0; $i < $n; $i ++) {
 $A[$i] = $i;
}
for($i; $i <  2 * $n; $i ++) {
 $A[$i] = $i + 0.1;
}

for($i = 0; $i < 2 * $n; $i ++) {
 println($A[$i]);
}


// function println($a) {
// echo $a;
// echo "\n";
// }


?>