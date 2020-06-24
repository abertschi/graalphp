<?php
$i = 20;
$a = 1;
while($i != 0) {
    $a = $a + $a;
    $i = $i - 1;
}
print $i;
print $a;

// same with do while
$i = 20;
$a = 1;
do {
    $a = $a + $a;
    $i = $i - 1;
} while ($i != 0);
print $i;
print $a;

?>
