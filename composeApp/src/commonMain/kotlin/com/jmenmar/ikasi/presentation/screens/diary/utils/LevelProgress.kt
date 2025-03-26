package com.jmenmar.ikasi.presentation.screens.diary.utils

data class LevelProgress(
    val level: Int,
    val currentLevelXp: Int,
    val totalXp: Int,
    val requiredXp: Int
)

fun calculateLevelAndProgress(totalXp: Int): LevelProgress {
    // Constantes
    val minLevel = 1
    val maxLevel = 100
    val baseXp = 120
    val xpIncreasePerLevel = 60

    // Calcular el nivel
    var currentLevel = minLevel
    var accumulatedXp = 0
    var xpNeededForCurrentLevel = 0

    if (totalXp > 0) {
        while (currentLevel < maxLevel) {
            xpNeededForCurrentLevel = calculateXpForLevel(currentLevel, baseXp, xpIncreasePerLevel)
            if (accumulatedXp + xpNeededForCurrentLevel <= totalXp) {
                accumulatedXp += xpNeededForCurrentLevel
                currentLevel++
            } else {
                break
            }
        }
    }

    // Calcular la experiencia en el nivel actual
    val currentLevelXp = totalXp - accumulatedXp

    // Calcular la experiencia necesaria para el nivel actual
    val requiredXp = calculateXpForLevel(currentLevel, baseXp, xpIncreasePerLevel)

    return LevelProgress(currentLevel, currentLevelXp, totalXp, requiredXp)
}

// Función para calcular la experiencia necesaria para un nivel dado (progresión lineal)
fun calculateXpForLevel(level: Int, baseXp: Int, xpIncreasePerLevel: Int): Int {
    return baseXp + (xpIncreasePerLevel * (level - 1))
}

fun formatMinutesToHours(minutes: Int): String {
    // Verificar si el valor es negativo
    if (minutes < 0) {
        return "Invalid value"
    }

    // Calcular las horas como un valor Float
    val hours = minutes / 60.0f

    // Formatear el valor Float de forma condicional
    val formattedHours = if (hours == hours.toInt().toFloat()) {
        // Si es un número entero, formatear sin decimales
        hours.toInt().toString()
    } else {
        // Si tiene decimales, formatear a un decimal
        val decimalPart = (hours - hours.toInt())
        val roundedDecimal = (decimalPart * 10).toInt() / 10.0
        (hours.toInt() + roundedDecimal).toString()
    }

    // Crear la cadena de texto formateada
    return "$formattedHours hrs"
}