function fib(n) {
    if (n < 2) {
        return 1;
    } else {
        return fib(n - 2) + fib(n -1);
    }
}

function main() {
n = 35;
	fib(n);
    fib(n);
    fib(n);
    fib(n);
    fib(n);
    fib(n);
    fib(n);
    fib(n);
    fib(n);
    fib(n);
    fib(n);
    fib(n);
    fib(n);
    fib(n);
    fib(n);
    fib(n);
    fib(n);
    fib(n);
    fib(n);
    fib(n);
    fib(n);
    fib(n);
    fib(n);
    fib(n);
    fib(n);
    fib(n);
    fib(n);
    fib(n);

    start = nanoTime();
    val = fib(n);
    end = nanoTime();
    println(val);
    println("computation time ns: " + (end - start));
    println("computation time us: " + (end - start) / 1000);
    println("computation time ms: " + (end - start) / 1000 / 1000);
}
