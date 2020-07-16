<?php


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
    println($stretchDepth);
    println(itemCheck($stretchTree));
    // printf("stretch tree of depth %d\t check: %d\n", $stretchDepth, itemCheck($stretchTree));
    // unset($stretchTree);

    $longLivedTree = bottomUpTree($maxDepth);

    $iterations = 1 << ($maxDepth);
    do
    {
        $check = 0;
        for($i = 1; $i <= $iterations; ++$i)
        {
            $t = bottomUpTree($minDepth);
            $check += itemCheck($t);
            // unset($t);
        }

        println($iterations);
        println($minDepth);
        println($check);
        // printf("%d\t trees of depth %d\t check: %d\n", $iterations, $minDepth, $check);

        $minDepth += 2;
        $iterations >>= 2;
    }
    while($minDepth <= $maxDepth);

    // printf("long lived tree of depth %d\t check: %d\n", $maxDepth, itemCheck($longLivedTree));
    println($maxDepth);
    println(itemCheck($longLivedTree));

}

doAlgorithm(12);

// function println($a) {
// echo $a . "\n";
// }

