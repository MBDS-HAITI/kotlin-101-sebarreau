package com.kotlin.one.Lesson3

class Magus(name: String) :
    Character(name, Weapon("Bâton de chêne", 12), 80) {

    private val healPower = 30

    override fun action(target: Character?) {
        if (target == null) {
            println("$name n'a aucune cible.")
            return
        }


        if (target.isAlive) {
            println("$name lance un soin sur ${target.name}.")
            target.heal(healPower)
        }
    }
}