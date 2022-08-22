package com.example.cleanarchitecture.data.cache.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.cleanarchitecture.data.cache.database.model.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(user: User): Long

    @Query("SELECT * FROM user")
    fun getUser(): Flow<User>

    @Delete
    suspend fun delete(user: User)

    @Transaction
    suspend fun deleteAndInsert(user: User) {
        delete(user)
        upsert(user)
    }
}