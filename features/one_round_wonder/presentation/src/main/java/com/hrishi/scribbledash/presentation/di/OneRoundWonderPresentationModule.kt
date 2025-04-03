package com.hrishi.scribbledash.presentation.di

import com.hrishi.scribbledash.presentation.one_round_wonder.OneRoundWonderViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val oneRoundWonderPresentationModule = module {
    viewModelOf(::OneRoundWonderViewModel)
}