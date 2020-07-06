<?php
// test simple array write, read
$n = 10;
$a = array();

for($i = 0; $i < $n; $i ++) {
    $a[$i] = $i;
}
for($i = 0; $i < $n; $i ++) {
    println($a[$i]);
}

// function println($a) {
// echo $a;
// echo "\n";
// }


?>