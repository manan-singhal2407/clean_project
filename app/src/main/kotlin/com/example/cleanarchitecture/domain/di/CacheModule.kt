package com.example.cleanarchitecture.domain.di

import android.app.Application
import androidx.room.Room
import com.example.cleanarchitecture.data.cache.database.LocalDatabase
import com.example.cleanarchitecture.data.cache.database.dao.CommunityDao
import com.example.cleanarchitecture.data.cache.database.dao.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import net.sqlcipher.database.SupportFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CacheModule {

    @Singleton
    @Provides
    fun provideDb(application: Application, masterKey: String): LocalDatabase {
        return Room
            .databaseBuilder(
                application,
                LocalDatabase::class.java,
                LocalDatabase.DATABASE_NAME
            )
            .openHelperFactory(SupportFactory(masterKey.toByteArray(Charsets.ISO_8859_1)))
            .build()
    }

    @Singleton
    @Provides
    fun provideUserDao(db: LocalDatabase): UserDao = db.userDao()

    @Singleton
    @Provides
    fun provideCommunityDao(db: LocalDatabase): CommunityDao = db.communityDao()
}