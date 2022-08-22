package com.example.cleanarchitecture.data.cache.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.example.cleanarchitecture.data.cache.database.model.Community
import kotlinx.coroutines.flow.Flow

@Dao
interface CommunityDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(community: Community): Long

    @Update
    suspend fun update(community: Community)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(communities: List<Community>): LongArray

    @Query("SELECT * FROM community WHERE id = :id")
    fun getCommunity(id: String): Flow<Community>

    @Query("SELECT * FROM community")
    fun getCommunities(): Flow<List<Community>>

    @Query("DELETE FROM community")
    fun deleteCommunities()

    @Transaction
    suspend fun deleteAndInsert(communities: List<Community>) {
        deleteCommunities()
        upsert(communities)
    }
}