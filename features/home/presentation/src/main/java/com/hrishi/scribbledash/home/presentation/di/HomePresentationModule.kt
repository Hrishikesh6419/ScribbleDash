package com.hrishi.scribbledash.home.presentation.di

import com.hrishi.scribbledash.home.presentation.home.HomeViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val homePresentationModule = module {
    viewModelOf(::HomeViewModel)
}