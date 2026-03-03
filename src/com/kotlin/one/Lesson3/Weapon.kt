package com.kotlin.one.Lesson3

data class Weapon(
    val name: String,
    val power: Int
) {
    override fun toString(): String =
        "$name (Power $power)"
}