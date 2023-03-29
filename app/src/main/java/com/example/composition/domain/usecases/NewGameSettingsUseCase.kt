package com.example.composition.domain.usecases

import com.example.composition.domain.entity.GameSettings
import com.example.composition.domain.entity.Level
import com.example.composition.domain.repository.GameRepository

class NewGameSettingsUseCase(
    private val gameRepository: GameRepository
) {

    operator fun invoke(level: Level): GameSettings {
        return gameRepository.getGameSettings(level)
    }
}