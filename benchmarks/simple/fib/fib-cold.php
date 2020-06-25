<?php

function fib($a) {
if ($a < 2) {
  return 1;
}
return fib($a - 1) + fib($a - 2);
}


$start=hrtime(true); // ns 
$val = fib(30);
$stop = hrtime(true);

echo "result value: " . $val . "\n";

// in us
echo "result in ns " . ($stop - $start) . "\n";
echo "result in us " . ($stop - $start) / 1000 . "\n";
echo "result in ms " . ($stop - $start) / 1000 / 1000 . "\n";

?>
