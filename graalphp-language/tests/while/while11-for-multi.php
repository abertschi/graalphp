<?php
// for groups can have side effects,
for($a = 1, $b = 1, $c = 1;  $b = $b + 1, $c = $b,  $a < 100; $b = $b +1, $a = $a + 1) {
}
print $a;
print $b;
print $c;
?>