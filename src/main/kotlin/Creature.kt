import kotlin.random.Random

abstract class Creature(
    attackValue: Int,
    defenceValue: Int,
    healthPointsValue: Int,
    minDamage: Int,
    maxDamage: Int
) {
    companion object {
        private const val MIN_ATTACK = 1
        private const val MAX_ATTACK = 30
        private const val DEFAULT_ATTACK = MIN_ATTACK

        private const val MIN_DEFENCE = 1
        private const val MAX_DEFENCE = 30
        private const val DEFAULT_DEFENCE = MIN_DEFENCE

        private const val MIN_HEALTH_POINTS = 0
        private const val MAX_HEALTH_POINTS = Int.MAX_VALUE
        private const val DEFAULT_HEALTH_POINTS = 1

        private const val MIN_DAMAGE = 1
        private const val MAX_DAMAGE = Int.MAX_VALUE
        private val DEFAULT_DAMAGE = IntRange(MIN_DAMAGE, 50)

        // Диапазоны допустимых значений для характеристик
        private val ATTACK_RANGE = IntRange(MIN_ATTACK, MAX_ATTACK)
        private val DEFENCE_RANGE = IntRange(MIN_DEFENCE, MAX_DEFENCE)
        private val HEALTH_POINTS_RANGE = IntRange(MIN_HEALTH_POINTS, MAX_HEALTH_POINTS)
        private val DAMAGE_RANGE = IntRange(MIN_DAMAGE, MAX_DAMAGE)

        private val SUCCESS_DICE_VALUES: List<Int> = listOf(5, 6)

    }

    private var attack: Int = DEFAULT_ATTACK
    private var defence: Int = DEFAULT_DEFENCE
    protected var fullHealthPoints: Int = DEFAULT_HEALTH_POINTS
    protected var currentHealthPoints: Int = DEFAULT_HEALTH_POINTS
    private var damageRange: IntRange = DEFAULT_DAMAGE
    protected var isDead: Boolean = false

    init {
        if (ATTACK_RANGE.contains(attackValue)) this.attack = attackValue
        else println("Невалидное значение атаки, дефолтное значение: $DEFAULT_ATTACK")

        if (DEFENCE_RANGE.contains(defenceValue)) this.defence = defenceValue
        else println("Невалидное значение защиты, дефолтное значение: $DEFAULT_DEFENCE")

        if (HEALTH_POINTS_RANGE.contains(healthPointsValue) && healthPointsValue != 0) {
            this.fullHealthPoints = healthPointsValue
            this.currentHealthPoints = healthPointsValue
        } else println("Невалидное значение здоровья, дефолтное значение: $DEFAULT_HEALTH_POINTS")

        if (
            DAMAGE_RANGE.contains(minDamage) &&
            DAMAGE_RANGE.contains(maxDamage) &&
            (maxDamage >= minDamage)
        ) this.damageRange = IntRange(minDamage, maxDamage)
        else println("Невалидный диапазон урона, дефолтный диапазон: $DEFAULT_DAMAGE")
    }

    fun hitTarget(targetCreature: Creature) {
        if (isDead) {
            println("Существо мертво! Атака невозможна.")
            return
        }
        if (targetCreature.isDead) {
            println("Противник уже мёртв.")
            return
        }
        var attackModifier = attack - targetCreature.defence + 1
        if (attackModifier <= 0) attackModifier = 1

        val diceRolls = List(attackModifier) { Random.nextInt(1, 6) }

        var isHitSuccess = false

        for (successDiceValue in SUCCESS_DICE_VALUES) {
            if (diceRolls.contains(successDiceValue)) {
                isHitSuccess = true
                break
            }
        }

        if (isHitSuccess) {
            val hitResultHealth = targetCreature.currentHealthPoints - damageRange.random()
            if (hitResultHealth <= 0) {
                targetCreature.currentHealthPoints = 0
                targetCreature.isDead = true
                println("Попадание! Противник убит!")
            }
            else {
                targetCreature.currentHealthPoints = hitResultHealth
                println("Попадание! Осталось здоровья у противника: ${targetCreature.currentHealthPoints}")
            }
        } else println("Промах!")
    }

    override fun toString(): String {
        return "\nАтака: $attack\n" +
                "Защита: $defence\n" +
                "Полное здоровье: $fullHealthPoints\n" +
                "Текущее здоровье: $currentHealthPoints\n" +
                "Диапазон урона: $damageRange\n" +
                "Мертво ли существо: $isDead\n"
    }

}