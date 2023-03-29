package com.example.composition.domain.entity

data class GameSettings (
    val maxSumValue: Int,
    val mixCountOfRightAnswers: Int,
    val minPercentOfRightAnswers: Int,
    val gameTimeInSeconds: Int,
)