<?php

println(1000 >> 2);   // 0x3E8 is shifted right 2 places
println(-1000 << 2);  // 0xFFFFFC18 is shifted left 5 places
println(123 >> 128);  // Shift count larger than bit width => result 0
println(123 << 33);   // For 32-bit integers the result is zero, otherwise
                      // it is 0x7B shifted left 33 places

println(1 << 1999);
println(1 >> 1999);
println(-1 >> 1999);
println(-1 << 1999);


// function println($a) {
//    echo $a . "\n";
// }


?>
