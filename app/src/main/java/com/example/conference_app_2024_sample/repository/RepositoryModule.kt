package com.example.conference_app_2024_sample.repository

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @ClassKey(SessionsRepository::class)
    @IntoMap
    fun bindSessionsRepository(): Any {
        return DefaultSessionsRepository()
    }

}