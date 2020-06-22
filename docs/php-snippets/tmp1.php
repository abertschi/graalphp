// <?php
// $foo = 1337;

// function test() {
//     global $foo;
//     echo "test: ";
//     echo $foo;
// }

// test();
// ?>

<?php
function main() {
    $foo = 1337;

  






function test() {
        global $foo; // does not work, no lexical scope!
        echo "test: ";
        echo $foo;
    }

    test();
}

main(); // call main with truffle
?>
