package com.hrishi.scribbledash.presentation.model

import com.hrishi.scribbledash.domain.model.common.DifficultySetting
import com.scribbledash.presentation.ui.R

enum class DifficultySettingUi(val displayNameResId: Int) {
    BEGINNER(R.string.difficulty_beginner),
    CHALLENGING(R.string.difficulty_challenging),
    MASTER(R.string.difficulty_master);

    fun toDomain(): DifficultySetting = when (this) {
        BEGINNER -> DifficultySetting.BEGINNER
        CHALLENGING -> DifficultySetting.CHALLENGING
        MASTER -> DifficultySetting.MASTER
    }

    companion object {
        fun fromDomain(domain: DifficultySetting): DifficultySettingUi = when (domain) {
            DifficultySetting.BEGINNER -> BEGINNER
            DifficultySetting.CHALLENGING -> CHALLENGING
            DifficultySetting.MASTER -> MASTER
        }
    }
}