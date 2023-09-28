class Player(
    attack: Int,
    defence: Int,
    healthPoints: Int,
    minDamage: Int,
    maxDamage: Int
) : Creature(
    attack,
    defence,
    healthPoints,
    minDamage,
    maxDamage
) {
    companion object {
        private const val FLASK_HEALING_MODIFIER = 0.3
    }

    private var healFlaskCount = 4

    fun heal() {
        if (this.isDead) {
            println("Существо мертво! Лечение невозможно.")
            return
        }
        if (healFlaskCount > 0) {
            healFlaskCount--
            val healResult = (this.currentHealthPoints + (this.fullHealthPoints * FLASK_HEALING_MODIFIER)).toInt()
            if (healResult > this.fullHealthPoints) this.currentHealthPoints = this.fullHealthPoints
            else this.currentHealthPoints = healResult
            println("Лечение успешно! Текущее здоровье: ${this.currentHealthPoints}")
        } else {
            println("Фляжка пополнения здоровья пуста!")
        }
    }
}
