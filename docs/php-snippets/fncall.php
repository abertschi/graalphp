<?php

// function test() {
//     echo "test";
//     $a = 1;

//     abc();
    
//     function abc() {
//         echo "test:abc";
//     }
// }

// function abc() {
//     echo "abc";
// }


// test();

test();

function test() {
    echo "test";
    
    function foo() {
        echo "test:abc";
    }

        foo();

        
}

function abc() {
    echo "abc";

    function foo() {
        echo "abc:foo";
    }
}






?>
