<?php
// test short circuit in conditions
$a = 1;

// according to short circuit logic (and so defined in spec)
// foo() should not execute since $a is already 1
// but it does with
// PHP 7.4.6 (cli) (built: May 12 2020 15:48:23) ( NTS )
// Copyright (c) The PHP Group

if ($a || foo()) {
print 2;
}

$a = 0;

if ($a || foo()) {
print 3;
}

function foo() {
print 1;
return 1;
}

?>