package com.example.driversapptest.presentation.di

import kotlinx.coroutines.CoroutineDispatcher

class DispatchersProvider(
    val io: CoroutineDispatcher,
    val default: CoroutineDispatcher,
    val main: CoroutineDispatcher
)