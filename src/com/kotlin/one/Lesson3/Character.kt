package com.kotlin.one.Lesson3

abstract class Character(
    val name: String,
    protected val weapon: Weapon,
    var health: Int
) {

    fun takeDamage(damage: Int) {
        health = (health - damage).coerceAtLeast(0)
        println("$name takes $damage damage. Remaining HP: $health")
    }

    fun heal(amount: Int) {
        health += amount
        println("$name is healed for $amount HP. Current HP: $health")
    }

    val isAlive: Boolean
        get() = health > 0

    override fun toString(): String =
        "$name [${this::class.simpleName}] - HP: $health - $weapon"

    abstract fun action(target: Character?)
}