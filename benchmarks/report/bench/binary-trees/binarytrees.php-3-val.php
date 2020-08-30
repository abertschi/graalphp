<?php 
/* The Computer Language Benchmarks Game
   https://salsa.debian.org/benchmarksgame-team/benchmarksgame/

   contributed by Peter Baltruschat
   modified by Levi Cameron
   *reset*
*/

function bottomUpTree($depth)
{
   if (!$depth) return array(-1,-1);
   $depth--;
   return array(
      bottomUpTree($depth),
      bottomUpTree($depth));
}

function itemCheck($treeNode) {
   return 1
      + ($treeNode[0][0] == -1 ? 1 : itemCheck($treeNode[0]))
      + ($treeNode[1][0] == -1 ? 1 : itemCheck($treeNode[1]));
}

function doAlgorithm($n) {
    $minDepth = 4;
    $maxDepth = max($minDepth + 2, $n);
    $stretchDepth = $maxDepth + 1;

    $stretchTree = bottomUpTree($stretchDepth);
    echo $stretchDepth . "\n";
    echo itemCheck($stretchTree) . "\n";
    // printf("stretch tree of depth %d\t check: %d\n", $stretchDepth, itemCheck($stretchTree));
    unset($stretchTree);

    $longLivedTree = bottomUpTree($maxDepth);

    $iterations = 1 << ($maxDepth);
    do
    {
        $check = 0;
        for($i = 1; $i <= $iterations; ++$i)
        {
            $t = bottomUpTree($minDepth);
            $check += itemCheck($t);
            unset($t);
        }
        
        echo $iterations . "\n";
        echo $minDepth . "\n";
        echo $check . "\n";
        // printf("%d\t trees of depth %d\t check: %d\n", $iterations, $minDepth, $check);
   
        $minDepth += 2;
        $iterations >>= 2;
    }
    while($minDepth <= $maxDepth);

    // printf("long lived tree of depth %d\t check: %d\n", $maxDepth, itemCheck($longLivedTree));
    echo $maxDepth . "\n";
    echo itemCheck($longLivedTree) . "\n";
    
}
// benchmark

$N = 21;
$iter = 50;

for($i = 0; $i < $iter; $i ++) {
    $start=hrtime(true);
    doAlgorithm($N);
    $stop=hrtime(true);

    $res = ($stop - $start) / 1000.0 / 1000.0;
    output($N, $iter, $i, $res);
}

function output($N, $iters, $iter, $val) {
    echo "binary-trees-val N/iters/iter/val;" . $N . ";" . $iters . ";" . $iter . ";" . $val . ";" . "\n";
}

?>
    
