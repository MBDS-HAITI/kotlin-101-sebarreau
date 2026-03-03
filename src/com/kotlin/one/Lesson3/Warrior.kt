package com.kotlin.one.Lesson3

class Warrior(name: String) :
    Character(name, Weapon("Épée d’acier", 25), 120) {

    override fun action(target: Character?) {
        if (target == null) {
            println("$name n'a aucune cible à attaquer.")
            return
        }

        val degats = weapon.power
        println("$name attaque ${target.name} avec son épée !")
        target.takeDamage(degats)
    }
}