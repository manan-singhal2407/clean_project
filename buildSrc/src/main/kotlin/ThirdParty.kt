object ThirdParty {
    const val sqlCipher = "net.zetetic:android-database-sqlcipher:${Versions.sqlCipher}"
    const val coil = "io.coil-kt:coil-compose:${Versions.coil}"
    const val timber = "com.jakewharton.timber:timber:${Versions.timber}"
    const val lottie = "com.airbnb.android:lottie-compose:${Versions.lottie}"
    const val rudderstack = "com.rudderstack.android.sdk:core:${Versions.rudderstack}"
    const val cleverTap = "com.clevertap.android:clevertap-android-sdk:${Versions.cleverTap}"
    const val avoInspectorRelease = "com.github.avohq:android-avo-inspector:prod:${Versions.avoInspector}" // Does not include the visual inspector
    const val avoInspectorDebug = "com.github.avohq.android-avo-inspector:dev:${Versions.avoInspector}" // Includes the visual inspector, a tool useful to monitor your analytics calls when developing
}