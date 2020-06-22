<?php
// test repeated assign of the same var
$i0 = 0;
$i0 = 5 + $i0;
$i0 = $i0 + 5;
$i0 = $i0 + 5 + 3;
print $i0;
?>
