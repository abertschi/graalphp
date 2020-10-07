<?php

$needle = "needle";
$haystack1 = "find the needle in the haystack";
print(str_contains($haystack1,$needle));
$haystack2 = "find the noodle in the haystack";
print(str_contains($haystack2,$needle));
print(str_contains("abc", ""));
print(str_contains("", ""));
