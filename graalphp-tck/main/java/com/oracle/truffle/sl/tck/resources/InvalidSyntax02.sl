function test(n) {
  a = 1;
  if (a > 0) {
    b = 10;
    println(b);
  } else {
    b = 20;
    a = 0;
    c = 1;
    if (b > 0) {
      a = -1;
      b = -1;
      c = -1;
      d = -1;
      print(d);
    }
  }
  println(b);
  println(a);
}
function main() {
  test(\"n_n\");
}
