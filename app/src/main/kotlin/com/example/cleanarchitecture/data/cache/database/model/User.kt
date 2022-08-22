package com.example.cleanarchitecture.data.cache.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.JsonClass

@Entity
@JsonClass(generateAdapter = true)
data class User(
    @PrimaryKey
    val username: String,
    val name: String?,
    val discordId: String
)

enum class UserType {
    NEW_USER,
    EXISTING_USER,
    ANONYMOUS_USER
}