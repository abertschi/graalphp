<?php

function fib($a) {
if ($a < 2) {
  return 1;
} else {
    return fib($a - 2) + fib($a - 1);    
}
}

$n = 35;
fib($n);
fib($n);
fib($n);
fib($n);
fib($n);
fib($n);
fib($n);
fib($n);
fib($n);
fib($n);
fib($n);
fib($n);
fib($n);
fib($n);
fib($n);
fib($n);
fib($n);
fib($n);
fib($n);
fib($n);
fib($n);
fib($n);
fib($n);
fib($n);
fib($n);
fib($n);
fib($n);
fib($n);

$start=hrtime(true); // ns 
$val = fib($n);
$stop = hrtime(true);

echo "result value: " . $val . "\n";

// in us
echo "result in ns " . ($stop - $start) . "\n";
echo "result in us " . ($stop - $start) / 1000 . "\n";
echo "result in ms " . ($stop - $start) / 1000 / 1000 . "\n";

?>
