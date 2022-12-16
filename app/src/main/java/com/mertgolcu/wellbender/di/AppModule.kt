package com.mertgolcu.wellbender.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import javax.inject.Qualifier
import javax.inject.Singleton

/**
 * Created by Mert Gölcü on 15.12.2022.
 */

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    // its empty for now but general injections or scope annotations will be here

    @ApplicationScope
    @Provides
    @Singleton
    fun provideApplicationScope() =
        CoroutineScope(SupervisorJob()) // Learn This specially "Coroutine"
}

@Retention(AnnotationRetention.RUNTIME)
@Qualifier
annotation class ApplicationScope