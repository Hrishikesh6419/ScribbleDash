package com.hrishi.scribbledash.presentation.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.hrishi.scribbledash.domain.model.common.DifficultySetting
import com.scribbledash.presentation.designsystem.R.drawable
import com.scribbledash.presentation.ui.R

enum class DifficultySettingUi(
    @StringRes val displayNameResId: Int,
    @DrawableRes val iconRes: Int
) {
    BEGINNER(R.string.difficulty_beginner, drawable.ic_beginner),
    CHALLENGING(R.string.difficulty_challenging, drawable.ic_challenging),
    MASTER(R.string.difficulty_master, drawable.ic_master);

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