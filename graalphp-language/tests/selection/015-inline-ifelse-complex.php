<?php
// tests A ? B : C; behavior

$a = 10 ? 1: 11;
p($a);
$a = 0 ? 1 : 2;     
p($a);
$i = 10;
$a = $i++ ? 1: -1;  

p($a);
p($i);

$i = 2;
p($i);
$a = $i++ ? f($i) : f(++$i); 
p($i);
p($a);

function f($int)
{
    return ($int > 1) ? $int * f($int - 1) : $int;
}

function p($a) {
    // echo $a . "\n";
    println($a);
}

?>
