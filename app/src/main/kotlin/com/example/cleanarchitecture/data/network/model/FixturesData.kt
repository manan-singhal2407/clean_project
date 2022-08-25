package com.example.cleanarchitecture.data.network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FixturesData(
    // todo incomplete data class
    @Json(name = "fixtureId")
    val fixtureId: String,

    @Json(name = "createdAt")
    val createdAt: String,

    @Json(name = "beginsAt")
    val beginsAt: String,

    @Json(name = "status")
    val status: String,

    @Json(name = "leagueName")
    val leagueName: String,

    @Json(name = "dream11Url")
    val dream11Url: String,
)