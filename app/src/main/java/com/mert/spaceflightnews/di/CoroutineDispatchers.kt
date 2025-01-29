package com.mert.spaceflightnews.di

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

open class CoroutineDispatchers @Inject constructor() {
    open val main: CoroutineDispatcher = Dispatchers.Main
    open val io: CoroutineDispatcher = Dispatchers.IO
    open val default: CoroutineDispatcher = Dispatchers.Default
    open val unconfined: CoroutineDispatcher = Dispatchers.Unconfined
}