package com.mert.spaceflightnews.di

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class CoroutineDispatchers @Inject constructor() {
    val main: CoroutineDispatcher = Dispatchers.Main
    val io: CoroutineDispatcher = Dispatchers.IO
    val default: CoroutineDispatcher = Dispatchers.Default
    val unconfined: CoroutineDispatcher = Dispatchers.Unconfined
}