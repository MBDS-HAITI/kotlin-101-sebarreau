package com.android.com.kotlin.one


//fun main() {
//    println(ex1())
//    println(ex2())
//    println(ex3())
//    println(ex4(listOf(12, 18, 25)))
//    println(ex5())
//    println(ex6())
//    println(ex7())
//    println(ex8())
//    println(ex9(listOf("Alice", "Bob", "Anna", "Charlie")))
//}


fun ex1(): List<Int> {
    return listOf(1, 2, 3, 4, 5)
}

fun ex2(): MutableList<String> {
    val items = mutableListOf("a", "b", "c")
    items.add("d")
    return items
}

fun ex3(): List<Int> {
    return (1..10).toList().filter { it % 2 == 0 }
}

fun ex4(ages: List<Int>): List<String> {
    return ages
        .filter { it >= 18 }
        .map { age -> "Adult: $age" }
}

fun ex5(): List<Int> {
    val nested = listOf(listOf(1, 2), listOf(3, 4), listOf(5))
    return nested.flatten()
}

fun ex6(): List<String> {
    val phrases = listOf("Kotlin is fun", "I love lists")
    return phrases.flatMap { it.split(" ") }
}

fun ex7(): List<Long> {
    val start = System.currentTimeMillis()

    val result = (1..1_000_000)
        .toList() // eager: crée une grosse liste
        .filter { it % 3 == 0 }
        .map { n -> n.toLong() * n.toLong() }
        .take(5)

    val end = System.currentTimeMillis()
    println("ex7 Time: ${end - start} ms")

    return result
}

fun ex8(): List<Long> {
    val start = System.currentTimeMillis()

    val result = (1..1_000_000)
        .asSequence() // lazy
        .filter { it % 3 == 0 }
        .map { n -> n.toLong() * n.toLong() }
        .take(5)
        .toList()

    val end = System.currentTimeMillis()
    println("ex8 Time: ${end - start} ms")

    return result
}

fun ex9(names: List<String>): List<String> {
    return names
        .filter { it.startsWith('A', ignoreCase = false) } // seulement 'A' majuscule
        .map { it.uppercase() }
        .sorted()
}