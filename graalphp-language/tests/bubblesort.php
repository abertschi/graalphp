<?php

function bubbleSort($arr, $size) {
    // $n;
    // $i;
    // $j;
    // $temp;
    $n = $size;

    $i = 0;
    while ($i < $n - 1) {
        $j = 0;
        while ($j < $n - $i - 1) {
            if ($arr[$j] > $arr[$j + 1]) {
                $temp = $arr[$j];
                $arr[$j] = $arr[$j + 1];
                $arr[$j + 1] = $temp;
            }
            $j = $j + 1;
        }
        $i = $i + 1;
    }

    return $arr;
}

function printArray($arr, $size) {
    // int n;
    // int i;
    $n = $size;

    $i = 0;
    while ($i < $n) {
        write($arr[$i]);
        $i = $i + 1;
    }

}

function write($a) {
    println($a);
}


$size = 10;
    
$arr = array();
$arr[0] = 100;
$arr[1] = 43;
$arr[2] = 1;
$arr[3] = -10;
$arr[4] = 1004;
$arr[5] = 34;
$arr[6] = 2;
$arr[7] = 2;
$arr[8] = 77;
$arr[9] = 12;

$arr = bubbleSort($arr, $size);
printArray($arr, $size);
  

?>