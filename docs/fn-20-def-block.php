<?php
// test function definitions
// conditionally defined functions (nested in some construct)
// need a define node which registers function once it is executed
// we currently do not support this yet

$a = 0;
while($a > 0) {
    function foo() {
       print 1337;
    }
}

foo();

?>