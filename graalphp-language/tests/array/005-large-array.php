<?php
// test large arrays

$n = 10000;
$A = array();
for($i = 0; $i < $n; $i ++) {
 $A[$i] = $i *$i;
}

for($i = 0; $i < $n; $i ++) {
 println($A[$i]);
}

// function println($a) {
// echo $a;
// echo "\n";
// }



?>