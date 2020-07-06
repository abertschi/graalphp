<?php
function foo() {
    return false;
}

function main() {
    if (0 == 1) {
        write(1);
    }
    if (0 != 1) {
        write(2);
    }
    if (1 == 0) {
        write(3);
    }
    if (1 != 0) {
        write(4);
    }



    if (0 <= 1) {
        write(3);
    }
    if (0 < 1) {
        write(4);
    }
    if (0 > 1) {
        write(5);
    }
    if (0 >= 1) {
        write(6);
    }


    if (1 == 0) {
        write(1);
    }
    if (1 != 0) {
        write(2);
    }
    if (1 <= 0) {
        write(3);
    }
    if (1 < 0) {
        write(4);
    }
    if (1 > 0) {
        write(5);
    }
    if (1 >= 0) {
        write(6);
    }
}

function write($val) {
    println($val);
//     echo $val ."\n";
}


main();

?>
