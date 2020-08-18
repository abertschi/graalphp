<?php

print FALSE ? 1 : (FALSE ? 2 : 3);
print FALSE ? 1 : (TRUE ? 2 : 3);
print (TRUE ? 1 : FALSE) ? 2 : 3;
print (TRUE ? 1 : TRUE) ? 2 : 3;

print FALSE ? 1 : (TRUE ? 2 : (TRUE ? 3 : 4));










