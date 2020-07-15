<?php

$n = 21;
$minDepth = 4;
$maxDepth = max($minDepth + 2, $n);
$stretchDepth = $maxDepth + 1;

$iterations = 1 << ($maxDepth);
do
{
    $check = 0;
    for($i = 1; $i <= $iterations; ++$i)
    {
        $t = 1 + $i;
        unset($t);
    }
    $minDepth += 2;
    $iterations >>= 2;
}
while($minDepth <= $maxDepth);
print($maxDepth);

?>

