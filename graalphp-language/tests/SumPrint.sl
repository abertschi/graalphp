function loop(n) {
  i = 0;  
  sum = 0;  
  while (i <= n) {  
    sum = sum + i;  
    i = i + 1;  
  }  
  return sum;  
}  

function main() {
  i = 0;
  while (i < 20) {
    loop(10000);
    i = i + 1;
  }
  println(loop(10000));  
}  
