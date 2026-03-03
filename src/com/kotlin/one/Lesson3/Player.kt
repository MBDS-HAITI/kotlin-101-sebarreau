package com.kotlin.one.Lesson3

class Player(val name: String) {

    private val team = mutableListOf<Character>()

    fun addCharacter(c: Character) {
        team.add(c)
    }

    fun hasCharacter(name: String): Boolean {
        return team.any { it.name.equals(name, ignoreCase = true) }
    }

    fun getTeam(): List<Character> {
        return team
    }

    fun alive(): MutableList<Character> {
        return team.filter { it.isAlive }.toMutableList()
    }

    val isDefeated: Boolean
        get() = alive().isEmpty()

    override fun toString(): String {
        val sb = StringBuilder("Team of $name:\n")
        for (c in team) {
            sb.append("  - ").append(c).append("\n")
        }
        return sb.toString()
    }
}