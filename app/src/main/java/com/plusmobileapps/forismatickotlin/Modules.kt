package com.plusmobileapps.forismatickotlin

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.engine.*
import io.ktor.client.engine.android.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HttpModule {

    @Singleton
    @Provides
    fun providesEngine(): HttpClientEngine = Android.create()

}

@Module
@InstallIn(SingletonComponent::class)
abstract class ForismaticModule {

    @Singleton
    @Binds
    abstract fun providesForismaticRepository(repositoryImpl: ForismaticRepositoryImpl): ForismaticRepository
}