<<__EntryPoint>>
function main(): void {

    $N = 21;
    $iter = 50;

    for($i = 0; $i < $iter; $i ++) {
        $start=clock_gettime_ns(1);
        doAlgorithm($N);
        $stop=clock_gettime_ns(1);

        $res = ($stop - $start) / 1000.0 / 1000.0;
        output($N, $iter, $i, $res);
    }
}

function bottomUpTree(int $depth): vec
{
    if (!$depth){
        $res= vec[-1, -1];
        return $res;
    }
    $depth--;
    return vec[bottomUpTree($depth), bottomUpTree($depth)];
}

function itemCheck(vec $treeNode): int {
    return 1
    + ($treeNode[0][0] == -1 ? 1 : itemCheck($treeNode[0]))
    + ($treeNode[1][0] == -1 ? 1 : itemCheck($treeNode[1]));
}

function doAlgorithm(int $n): void {
    $minDepth = 4;
    $maxDepth = max($minDepth + 2, $n);
    $stretchDepth = $maxDepth + 1;

    $stretchTree = bottomUpTree($stretchDepth);
    echo $stretchDepth . "\n";
    echo itemCheck($stretchTree) . "\n";

    unset($stretchTree);

    $longLivedTree = bottomUpTree($maxDepth);

    $iterations = 1 << ($maxDepth);
    do {
        $check = 0;
        for($i = 1; $i <= $iterations; ++$i) {
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
    } while($minDepth <= $maxDepth);

    // printf("long lived tree of depth %d\t check: %d\n", $maxDepth, itemCheck($longLivedTree));
    echo $maxDepth . "\n";
    echo itemCheck($longLivedTree) . "\n";

}
function output(int $N, int $iters, int $iter, float $val):void {
    echo "binary-trees-val N/iters/iter/val;" . $N . ";" . $iters . ";" . $iter . ";" . $val . ";" . "\n";
}




