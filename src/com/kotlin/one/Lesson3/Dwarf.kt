package com.kotlin.one.Lesson3

class Dwarf(name: String) :
    Character(name, Weapon("Hache runique", 34), 70) {

    override fun action(target: Character?) {
        if (target == null) {
            println("$name n'a aucune cible à attaquer.")
            return
        }

        val dmg = weapon.power
        println("$name frappe ${target.name} avec sa hache !")
        target.takeDamage(dmg)
    }
}