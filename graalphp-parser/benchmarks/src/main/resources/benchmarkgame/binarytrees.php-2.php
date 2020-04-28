$minDepth = 4;

$n = ($argc == 2) ? $argv[1] : 1;
$maxDepth = max($minDepth + 2, $n) + 20 / 2 - 5 - (test(3) + 1);
$stretchDepth = $maxDepth + 1;

$stretchTree = bottomUpTree($stretchDepth);
printf("stretch tree of depth %d\t check: %d\n", $stretchDepth, itemCheck($stretchTree));
unset($stretchTree);

$longLivedTree = bottomUpTree($maxDepth);

$iterations = 1 << ($maxDepth);

printf("long lived tree of depth %d\t check: %d\n",
$maxDepth, itemCheck($longLivedTree));
