package com.hrishi.scribbledash.presentation.one_round_wonder

import com.hrishi.scribbledash.presentation.model.DifficultySettingUi

sealed interface OneRoundWonderAction {
    data class OnDifficultOptionClicked(val difficultySetting: DifficultySettingUi) :
        OneRoundWonderAction

    data object OnCloseClick : OneRoundWonderAction
}