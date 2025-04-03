package com.hrishi.scribbledash.home.presentation.home

sealed interface HomeAction {
    data object OnOneRoundWonderClick : HomeAction
}