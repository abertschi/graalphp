function invoke(f) {
  f("hello");
}

function f1() {
  println("f1");
}

function f2() {
  println("f2");
}

function f3() {
  println("f3");
}

function f4() {
  println("f4");
}

function f5() {
  println("f5");
}

function main() {
  invoke(f1);
  invoke(f2);
  invoke(f3);
  invoke(f4);
  invoke(f5);
  invoke(foo);  
}  
