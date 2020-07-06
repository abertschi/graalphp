<?php
$A = array();
$B = array();
$C = array();
$n = 100;
for($i = 0; $i < $n; $i ++ ) {
$B[$i] = $i * $i + 0.1;
}

for($i = 0; $i < $n; $i ++ ) {
$C[$i] = $i * $i + 0.2;
}
$A[0] = $B;
$A[1] = $C;

for($i = 0; $i < $n; $i ++ ) {
print($A[0][$i]);
}

for($i = 0; $i < $n; $i ++ ) {
print($A[1][$i]);
}
?>