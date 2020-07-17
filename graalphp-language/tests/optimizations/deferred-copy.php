<?php

function bottomUpTree($depth)
{
   if (!$depth) return array(-1,-1);
   $depth--;
   return array(
      bottomUpTree($depth),
      bottomUpTree($depth));
}

$A = bottomUpTree(18);
// $A[0] = 0;













?>