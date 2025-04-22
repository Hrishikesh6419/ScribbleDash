package com.hrishi.scribbledash.home.presentation.one_round_wonder

import com.hrishi.scribbledash.home.presentation.model.DifficultySettingUi

sealed interface OneRoundWonderAction {
    data class OnDifficultOptionClicked(val difficultySetting: DifficultySettingUi) :
        OneRoundWonderAction

    data object OnCloseClick : OneRoundWonderAction
}