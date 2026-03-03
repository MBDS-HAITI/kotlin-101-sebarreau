package com.kotlin.one.Lesson3

import java.util.Scanner

class Game {
    private val scanner = Scanner(System.`in`)

    private lateinit var player1: Player
    private lateinit var player2: Player

    fun startGame() {
        println("Welcome to our Battle Arena game!")

        // === Player 1 Name ===
        var name1: String
        do {
            print("Player 1 Name: ")
            name1 = scanner.nextLine().trim()

            when {
                name1.isEmpty() -> println("Name cannot be empty. Try again!")
                name1.contains(" ") -> {
                    println("Name cannot contain spaces. Try again!")
                    name1 = ""
                }
            }
        } while (name1.isEmpty())

        // === Player 2 Name ===
        var name2: String
        do {
            print("Player 2 Name: ")
            name2 = scanner.nextLine().trim()

            when {
                name2.isEmpty() -> println("Name cannot be empty. Try again!")
                name2.equals(name1, ignoreCase = true) -> {
                    println("Players must have different names. Try again!")
                    name2 = ""
                }
                name2.contains(" ") -> {
                    println("Name cannot contain spaces. Try again!")
                    name2 = ""
                }
            }
        } while (name2.isEmpty())

        player1 = Player(name1)
        player2 = Player(name2)

        // === Team creation ===
        println("\nTeam Creation...")
        createTeam(player1)
        createTeam(player2)

        // === Start battle ===
        println("\n--- The battle begins! ---")
        battle()
    }

    private fun createTeam(player: Player) {
        println("\n${player.name}, choose your 3 characters.")
        val available = mutableListOf("Warrior", "Magus", "Dwarf", "Colossus")

        for (i in 1..3) {
            println("\nChoose character type $i (each type only once):")
            for (j in available.indices) {
                println("${j + 1}) ${available[j]}")
            }
            print("> ")
            val choice = readIntBetween(1, available.size)
            val chosenType = available[choice - 1]

            // Ask for a unique and valid character name
            var characterName: String
            do {
                print("Please enter a name for your character: ")
                characterName = scanner.nextLine().trim()

                when {
                    characterName.isEmpty() -> println("Character name cannot be empty!")
                    characterName.contains(" ") -> {
                        println("Character name cannot contain spaces!")
                        characterName = ""
                    }
                    player.hasCharacter(characterName) -> {
                        println("You already have a character with this name!")
                        characterName = ""
                    }
                }
            } while (characterName.isEmpty() || player.hasCharacter(characterName))

            val character = when (chosenType) {
                "Warrior" -> Warrior(characterName)
                "Magus" -> Magus(characterName)
                "Dwarf" -> Dwarf(characterName)
                "Colossus" -> Colossus(characterName)
                else -> throw IllegalArgumentException("Invalid type: $chosenType")
            }
            player.addCharacter(character)
            println("$characterName ($chosenType) has been added to your team!")
            available.remove(chosenType)
        }
    }

    private fun battle() {
        var active: Player = player1
        var rival: Player = player2
        var turn = 1

        while (!player1.isDefeated && !player2.isDefeated) {
            println("\n--- Turn $turn: ${active.name} ---")
            displayTeams()

            val activeCharacter = chooseCharacter(active.alive(), "Choose a character to act")

            if (activeCharacter is Magus) {
                println("1) Attack  2) Heal")
                val choice = readIntBetween(1, 2)

                if (choice == 1) {
                    val target = chooseCharacter(rival.alive(), "Choose a target to attack")
                    activeCharacter.action(target)
                } else {
                    val target = chooseCharacter(active.alive(), "Choose an ally to heal")
                    activeCharacter.action(target)
                }
            } else {
                val target = chooseCharacter(rival.alive(), "Choose a target to attack")
                activeCharacter.action(target)
            }

            if (rival.isDefeated) break

            // Switch turns
            val temp = active
            active = rival
            rival = temp
            turn++
        }

        println("\n=== End of the battle ===")
        if (player1.isDefeated) {
            println("Victory for ${player2.name}!")
        } else {
            println("Victory for ${player1.name}!")
        }
    }

    private fun displayTeams() {
        println("\nTeam status:")
        println(player1)
        println(player2)
    }

    private fun chooseCharacter(list: MutableList<Character>, message: String): Character {
        println("\n$message:")
        for (i in list.indices) {
            println("${i + 1}) ${list[i]}")
        }
        print("> ")
        val choice = readIntBetween(1, list.size)
        return list[choice - 1]
    }

    private fun readIntBetween(min: Int, max: Int): Int {
        while (true) {
            val raw = scanner.nextLine().trim()
            val n = raw.toIntOrNull()
            if (n != null && n in min..max) return n
            print("Invalid entry, choose a number between $min and $max: ")
        }
    }
}