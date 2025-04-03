package com.hrishi.scribbledash.presentation.one_round_wonder

import com.hrishi.scribbledash.presentation.model.DifficultySettingUi
import kotlin.enums.EnumEntries

data class OneRoundWonderViewState(
    val difficultySettings: EnumEntries<DifficultySettingUi> = DifficultySettingUi.entries
)