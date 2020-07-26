<?php
// result is float, if we print it and it is a integer number, php will ignore the .0 digit

$a = sqrt(4);
println($a);

$a = sqrt(4.5);
println($a);

$a = sqrt(True);
println($a);

$a = sqrt(False);
println($a);

// function println($a){
// echo $a . "\n";
// }
?>