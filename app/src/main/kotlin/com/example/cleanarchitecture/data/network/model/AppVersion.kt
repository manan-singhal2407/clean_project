package com.example.cleanarchitecture.data.network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AppVersion(
    @Json(name = "versionCode")
    val versionCode: Int,

    @Json(name = "versionName")
    val versionName: String,

    @Json(name = "versionCode")
    val forceUpdateCode: Int,

    @Json(name = "versionCode")
    val versionInfo: String?
)
