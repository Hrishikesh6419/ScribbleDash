package com.hrishi.scribbledash.home.presentation.di

import com.hrishi.scribbledash.home.presentation.drawing.OneRoundDrawingViewModel
import com.hrishi.scribbledash.home.presentation.home.HomeViewModel
import com.hrishi.scribbledash.home.presentation.one_round_wonder.OneRoundWonderViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val homePresentationModule = module {
    viewModelOf(::HomeViewModel)
    viewModelOf(::OneRoundWonderViewModel)
    viewModelOf(::OneRoundDrawingViewModel)
}