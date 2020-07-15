<?php /* The Computer Language Benchmarks Game
         https://salsa.debian.org/benchmarksgame-team/benchmarksgame/

         contributed by Isaac Gouy, transliterated from Mike Pall's Lua program
      */

// modifications to version on benchmarksgame website
// remove list() builtin and assign variables explicitly
// remove strings from printf and print result values directly
// use a fixed N instead of command line argument
// introduce warm up and timing

function Fannkuch($n){
    $p = $q = $s = array();
    $sign = 1; $maxflips = $sum = 0; $m = $n-1;
    for ($i=0; $i<$n; $i++){ $p[$i] = $i; $q[$i] = $i; $s[$i] = $i; }
    do {
        // Copy and flip.
        $q0 = $p[0];                                          // Cache 0th element.
        if ($q0 != 0){
            for($i=1; $i<$n; $i++) $q[$i] = $p[$i];            // Work on a copy.
            $flips = 1;
            do {
                $qq = $q[$q0];
                if ($qq == 0){                                  // ... until 0th element is 0.
                    $sum += $sign*$flips;
                    if ($flips > $maxflips) $maxflips = $flips;  // New maximum?
                    break;
                }
                $q[$q0] = $q0;
                if ($q0 >= 3){
                    $i = 1; $j = $q0 - 1;
                    do { $t = $q[$i]; $q[$i] = $q[$j]; $q[$j] = $t; $i++; $j--; } while ($i < $j);
                }
                $q0 = $qq; $flips++;
            } while (true);
        }
        // Permute.
        if ($sign == 1){
            $t = $p[1]; $p[1] = $p[0]; $p[0] = $t; $sign = -1; // Rotate 0<-1.
        } else {
            $t = $p[1]; $p[1] = $p[2]; $p[2] = $t; $sign = 1;  // Rotate 0<-1 and 0<-1<-2.
            for($i=2; $i<$n; $i++){
                $sx = $s[$i];
                if ($sx != 0){ $s[$i] = $sx-1; break; }
                if ($i == $m) return array($sum,$maxflips);     // Out of permutations.
                $s[$i] = $i;
                // Rotate 0<-...<-i+1.
                $t = $p[0]; for($j=0; $j<=$i; $j++){ $p[$j] = $p[$j+1]; } $p[$i+1] = $t;
            }
        }
    } while (true);
}


$N = 12;
$iter = 10;

for($i = 0; $i < $iter; $i ++) {
    $start=hrtime(true);
    $A = Fannkuch($N);
    $stop=hrtime(true);

    $res = ($stop - $start) / 1000.0 / 1000.0;
    output($N, $iter, $i, $res);
}

function output($N, $iters, $iter, $val) {
    echo "fannkuch N/iters/iter/val;" . $N . ";" . $iters . ";" . $iter . ";" . $val . ";" . "\n";
}
?>
