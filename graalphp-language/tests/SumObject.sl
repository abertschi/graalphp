function loop(n) {
  obj = new();
  obj.i = 0;  
  obj.sum = 0;  
  while (obj.i <= n) {  
    obj.sum = obj.sum + obj.i;  
    obj.i = obj.i + 1;  
  }  
  return obj.sum;  
}  

function main() {
  i = 0;
  while (i < 20) {
    loop(10000);
    i = i + 1;
  }
  println(loop(10000));  
}  
