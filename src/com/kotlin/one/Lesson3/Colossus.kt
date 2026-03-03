package com.kotlin.one.Lesson3

class Colossus(name: String) :
    Character(name, Weapon("Marteau de pierre", 22), 180) {

    override fun action(target: Character?) {

        if (target == null) {
            println("$name n'a aucune cible à attaquer.")
            return
        }

        val dmg = weapon.power
        println("$name écrase ${target.name} avec son marteau !")

        target.takeDamage(dmg)
    }
}