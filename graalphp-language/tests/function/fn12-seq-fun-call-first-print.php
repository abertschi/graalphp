<?php
// test sequenced function invocations call before def
print 1;
print 2;

print c(a(1), b(b(b(2))));


function a($a) {
 $b = 10;
 print $c = $a + $b;
 return $c;
}


function b($c) {
print $c = $c + a($c);
return $c + 1;
}

function c($a, $b) {
$x = a(b($a + $b));
print $x;
return $x;
}
?>