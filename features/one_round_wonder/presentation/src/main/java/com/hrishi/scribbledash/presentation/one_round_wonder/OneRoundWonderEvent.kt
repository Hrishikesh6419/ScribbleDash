package com.hrishi.scribbledash.presentation.one_round_wonder

import com.hrishi.scribbledash.domain.model.common.DifficultySetting

sealed interface OneRoundWonderEvent {
    data class NavigateToDrawingScreen(val difficultySetting: DifficultySetting) :
        OneRoundWonderEvent

    data object NavigateBack : OneRoundWonderEvent
}