<?php
// test short circuit in conditions
$a = 1;

if ($a && foo()) {
print 2;
}

$a = 0;

if ($a && foo()) {
print 3;
}

function foo() {
print 1;
return 1;
}

?>