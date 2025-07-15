package com.example.ai36

class Calculator {

    fun add(a: Int, b: Int): Int {
        return a + b;
    }

    fun subtract(a: Int, b: Int): Int {
        return a - b;
    }

    fun multiply(a: Int, b: Int): Int {
        return a * b;
    }

    fun divide(a: Int, b: Int): Int {
        return a / b;
    }

    fun mod(a: Int, b: Int): Int {
        return a % b;
    }

    fun power(a: Int, b: Int): Int {
        var result = 1
        for (i in 1..b) {
            result *= a
        }
        return result
    }


}