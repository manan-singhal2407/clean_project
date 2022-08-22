package com.example.cleanarchitecture.domain.di

import android.app.Application
import android.content.Context
import com.example.cleanarchitecture.presentation.base.navigation.Navigator
import com.google.android.play.core.appupdate.AppUpdateManager
import com.google.android.play.core.appupdate.AppUpdateManagerFactory
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideApplication(@ApplicationContext application: Context): Application =
        application as Application

    @Singleton
    @Provides
    fun provideApplicationContext(@ApplicationContext context: Context): Context = context

    @Singleton
    @Provides
    fun provideMoshi(): Moshi = Moshi.Builder().build()

    @Singleton
    @Provides
    fun provideAppUpdateManager(application: Context): AppUpdateManager = AppUpdateManagerFactory.create(application)

    @Singleton
    @Provides
    fun provideNavigator(): Navigator = Navigator()
}