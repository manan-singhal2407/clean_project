package com.example.cleanarchitecture.domain.di

import com.example.cleanarchitecture.presentation.screen.home.HomeRepository
import com.example.cleanarchitecture.presentation.screen.login.LoginRepository
import com.example.cleanarchitecture.presentation.screen.version.VersionRepository
import com.google.firebase.database.DatabaseReference
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

    @ViewModelScoped
    @Provides
    fun providesLoginRepository(): LoginRepository {
        return LoginRepository()
    }

    @ViewModelScoped
    @Provides
    fun providesHomeRepository(): HomeRepository {
        return HomeRepository()
    }

    @ViewModelScoped
    @Provides
    fun providesVersionRepository(
        databaseReference: DatabaseReference,
        moshi: Moshi
    ): VersionRepository {
        return VersionRepository(databaseReference, moshi)
    }
}