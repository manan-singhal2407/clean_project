package com.example.cleanarchitecture.data.network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AppVersion(
    @Json(name = "versionCode")
    val versionCode: Long,

    @Json(name = "versionName")
    val versionName: String,

    @Json(name = "forceUpdateCode")
    val forceUpdateCode: Long,

    @Json(name = "versionInfo")
    val versionInfo: String
)