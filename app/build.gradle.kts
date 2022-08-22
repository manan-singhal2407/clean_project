plugins {
    id(Plugin.application)
    id(Plugin.android)
    id(Plugin.kapt)
    id(Plugin.hilt)
    id(Plugin.junit)
    id(Plugin.googleServices)
    id(Plugin.firebaseCrashlytics)
    id(Plugin.detekt).version(Versions.detekt)
}

android {
    compileSdk = Versions.compileSdk

    defaultConfig {
        applicationId = "com.example.cleanarchitecture"
        minSdk = Versions.minSdk
        targetSdk = Versions.targetSdk
        versionCode = SemanticVersion.versionCode
        versionName = SemanticVersion.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
        signingConfig = signingConfigs.getByName("debug")
    }

    buildTypes {
        debug {
            applicationIdSuffix = ".debug"
            isDefault = true
            isDebuggable = true
            isMinifyEnabled = false
            isShrinkResources = false
            isCrunchPngs = false
            isTestCoverageEnabled = true
            extra["enableCrashlytics"] = false
            extra["alwaysUpdateBuildId"] = false
        }

        release {
            isDebuggable = false
            isMinifyEnabled = true
            isShrinkResources = true
            isCrunchPngs = true
            versionNameSuffix = "-${System.currentTimeMillis()}"
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
            )
        }

        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_11
            targetCompatibility = JavaVersion.VERSION_11
        }
        kotlinOptions {
            jvmTarget = JavaVersion.VERSION_11.toString()
        }
        tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
            kotlinOptions {
                freeCompilerArgs = freeCompilerArgs + "-Xopt-in=kotlin.RequiresOptIn"
            }
        }
        buildFeatures {
            compose = true
        }
        composeOptions {
            kotlinCompilerExtensionVersion = Versions.compose
        }
        packagingOptions {
            resources.excludes.addAll(Misc.packageOptions)
        }
        namespace = "com.example.cleanarchitecture"
    }

    dependencies {
        implementation(AndroidX.coreKtx)
        implementation(AndroidX.appCompat)
        implementation(AndroidX.constraintLayout)
        implementation(AndroidX.uiTooling)
        implementation(AndroidX.navFragmentKtx) // will remove later
        implementation(AndroidX.navUiKtx) // will remove later
        implementation(AndroidX.composeUi)
        implementation(AndroidX.composeUtil)
        implementation(AndroidX.composeFoundation)
        implementation(AndroidX.composeMaterial)
        implementation(AndroidX.composeMaterialWindowSize)
        implementation(AndroidX.composeIconsCore)
        implementation(AndroidX.composeIconsExtended)
        implementation(AndroidX.constraintCompose)
        implementation(AndroidX.navigationCompose)
        implementation(AndroidX.hiltNavigationCompose)
        implementation(AndroidX.composeActivity)
        implementation(AndroidX.roomRuntime)
        implementation(AndroidX.roomKtx)
        implementation(AndroidX.datastore)
        implementation(AndroidX.paging)
        implementation(Google.hiltAndroid)
        implementation(platform(Google.googleServicesBom))
        implementation(Google.googleAdsServices)

        implementation(Firebase.auth)
        implementation(Firebase.realtimeDatabase)
        implementation(Firebase.storage)
        implementation(Firebase.analytics)
        implementation(Firebase.crashlytics)
        implementation(Firebase.messaging)

        implementation(ThirdParty.coil)
        implementation(ThirdParty.timber)
        implementation(ThirdParty.lottie)
        implementation(ThirdParty.sqlCipher)

        implementation(Jetbrains.kotlinStdlib)

        implementation(Square.retrofit)
        implementation(Square.retrofitMoshiConvertor)
        implementation(Square.moshi)
        implementation(Square.okHttp)
        debugImplementation(Square.leakCanary)
        implementation(Square.okhttpLogger)

        implementation(Accompanist.pager)

        detektPlugins(DetektPlugin.formatting)
        detektPlugins(DetektPlugin.compose)

        kapt(AnnotationProcessing.hiltCompiler)
        kapt(AnnotationProcessing.roomCompiler)
        kapt(AnnotationProcessing.moshiAnnotationProcessor)

        implementation(Google.playInstallReferrer)
        implementation(Google.playCore)

        // TESTING
        testImplementation(UnitTest.jupiterApi)
        testImplementation(UnitTest.junitPlatformSuiteEngine)
        testRuntimeOnly(UnitTest.jupiterEngine)
        testImplementation(UnitTest.mockk)

        // Mock web server
        testImplementation(UnitTest.mockWebServer)
        // testImplementation(UnitTest.okHttp) // include this if you get issues

        // compose testing
        implementation(InstrumentationTest.composeUi)



        // Analytics and related
//        implementation(ThirdParty.rudderstack)
//        implementation(ThirdParty.cleverTap)
//        implementation("com.rudderstack.android.integration:clevertap:1.0.1")
//        implementation("com.google.code.gson:gson:2.9.1")
//        implementation("com.android.installreferrer:installreferrer:2.2")
        // Includes the visual inspector, a tool useful to monitor your analytics calls when developing
//        debugImplementation(ThirdParty.avoInspectorDebug)
        // Does not include the visual inspector
//        implementation(ThirdParty.avoInspectorDebug)
//        releaseImplementation(ThirdParty.avoInspectorRelease)
    }
}

detekt {
    buildUponDefaultConfig = true // preconfigure defaults
    allRules = false // activate all available (even unstable) rules.
    autoCorrect = true // automatically formats your code
    config =
        files("$projectDir/config/detekt.yml") // point to your custom config defining rules to run, overwriting default behavior
}

tasks.withType<io.gitlab.arturbosch.detekt.Detekt>().configureEach {
    reports {
        html.required.set(true) // observe findings in your browser with structure and code snippets
        xml.required.set(false) // checkstyle like format mainly for integrations like Jenkins
        txt.required.set(false) // similar to the console output, contains issue signature to manually edit baseline files
        sarif.required.set(false) // standardized SARIF format (https://sarifweb.azurewebsites.net/) to support integrations with Github Code Scanning
    }
}

tasks.withType<io.gitlab.arturbosch.detekt.Detekt>().configureEach {
    jvmTarget = "1.8"
}
tasks.withType<io.gitlab.arturbosch.detekt.DetektCreateBaselineTask>().configureEach {
    jvmTarget = "1.8"
}
tasks.withType<Test> { useJUnitPlatform() }

tasks {
    create<Copy>(name = "InstallGitHook") {
        this.group = "help"
        this.from("${rootProject.rootDir}/scripts/")
            .into("${rootProject.rootDir}/.git/hooks/").eachFile {
                fileMode = 1100000111
            }
    }
}

tasks.getByPath(":app:preBuild").dependsOn("InstallGitHook")