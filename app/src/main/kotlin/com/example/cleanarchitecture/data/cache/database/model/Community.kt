package com.example.cleanarchitecture.data.cache.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@Entity
@JsonClass(generateAdapter = true)
data class Community(
    @PrimaryKey
    @Json(name = "guildId")
    val id: String,

    @Json(name = "icon")
    val icon: String?,

    @Json(name = "name")
    val name: String,

    @Json(name = "owner")
    val owner: String = "",

    @Json(name = "existsAsCommunity")
    val isOnSuperShare: Boolean,

    @Json(name = "member")
    var isUserMember: Boolean = false,

    @Json(name = "memberCount")
    val memberCount: Long = 0,

    @Json(name = "hasNominated")
    var isNominated: Boolean = false,

    @Json(name = "nominationCount")
    val nominationCount: Long = 0
)