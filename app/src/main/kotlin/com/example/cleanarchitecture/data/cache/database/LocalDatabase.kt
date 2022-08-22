package com.example.cleanarchitecture.data.cache.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.cleanarchitecture.data.cache.database.dao.CommunityDao
import com.example.cleanarchitecture.data.cache.database.dao.UserDao
import com.example.cleanarchitecture.data.cache.database.model.Community
import com.example.cleanarchitecture.data.cache.database.model.User

@Database(entities = [User::class, Community::class], version = 1)
abstract class LocalDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    abstract fun communityDao(): CommunityDao

    companion object {
        const val DATABASE_NAME: String = "PrivateContest"
    }
}