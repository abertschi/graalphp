<?php

// test postfix implementation
$a = 1;

$a++;
$a++;

print $a;


$a --;
$a --;
print $a;

$a = 0;
for($i = 0; $i < 10; $i++) {
$a = $a + 1;
}
print $a;

$a = 0;
for($i = 10; $i > 0; $i--) {
$a = $a + 1;
}
print $a;

?>