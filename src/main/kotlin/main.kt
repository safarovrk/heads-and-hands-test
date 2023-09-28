fun main(args: Array<String>) {

    val monsterCreature = Monster(
        attack = 30,
        defence = 12,
        healthPoints = 100,
        minDamage = 10,
        maxDamage = 50
    )
    val playerCreature = Player(
        attack = 27,
        defence = 21,
        healthPoints = 200,
        minDamage = 64,
        maxDamage = 530
    )

    println("Монстр:\n$monsterCreature")
    println("Игрок:\n$playerCreature")

    println("Монстр атакует игрока:\n")

    monsterCreature.hitTarget(playerCreature)
    monsterCreature.hitTarget(playerCreature)
    monsterCreature.hitTarget(playerCreature)
    monsterCreature.hitTarget(playerCreature)
    monsterCreature.hitTarget(playerCreature)
    monsterCreature.hitTarget(playerCreature)

    println("\nИгрок лечится:\n")

    playerCreature.heal()
    playerCreature.heal()
    playerCreature.heal()
    playerCreature.heal()
    playerCreature.heal()

    println("\nИгрок наносит ответный удар!\n")

    playerCreature.hitTarget(monsterCreature)

}