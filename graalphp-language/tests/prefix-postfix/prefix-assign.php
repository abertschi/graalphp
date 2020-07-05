<?php
// test postfix assign behaviour

$a = 0;
$c = --$a;
print $c;
print $a;

$c = 0;
$a = 0;
$c = ++$a;
print $c;
print $a;

$c = 0;
$a = 0;
$c = --$a + ++$a;
print $c;

$c = 0;
$a = 0;
$c = ++$a + ++$a;
print $c;

$c = 0;
$a = 0;
$c = --$a + --$a;
print $c;

$c = 0;
$a = 0;
$c = --$a + --$a + --$a + --$a  + --$a + --$a + --$a + --$a;
print $c;

$c = 0;
$a = 0;
$c = ++$a + ++$a + ++$a + ++$a  + ++$a + ++$a + ++$a + ++$a;
print $c;
?>