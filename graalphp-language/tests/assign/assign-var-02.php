<?php
// $r1, $r2, $r3;
// $i0,  $i1,  $i2,  $i3,  $i4,  $i5, $i6, $i7;
		
$i0 = 0;
$i1 = 1;
$i2 = 2;
$i3 = 3;
$i4 = 4;
$i5 = 5;
$i6 = 6;
$i7 = 7;
		
$r1 = $i0 + ($i1 + ( $i2 + ( $i3 + ( $i4 + ($i5 + ($i6 + $i7))))));
$r2 = (((((($i0 + $i1) + $i2) +  $i3) +  $i4) +  $i5) + $i6) + $i7;
$r3 = (($i0 + $i1) + ($i2 + $i3)) + (($i4 + $i5) + ($i6 + $i7));

// we dont support print yet, so last expressoin will returned in test mode

$r2;
print $r2;
?>
