<?php
// test sequenced function invocations
function a($a) {
 $b = 10;
 $c = $a + $b;
 return $c;
}


function b($c) {
$c = $c + a($c);
return $c + 1;
}


function c($a, $b) {
return a(b($a + $b));
}

// echo c(a(1), b(b(b(2))));
c(a(1), b(b(b(2))));
?>