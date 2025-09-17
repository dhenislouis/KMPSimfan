
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.jetbrains.kotlin.serialization)
    alias(libs.plugins.googleServices)
}

kotlin {
    androidTarget {
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }
    
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
        }
    }
    
    sourceSets {
        androidMain.dependencies {
            implementation(compose.preview)
            implementation(libs.androidx.activity.compose)
            implementation(libs.koin.android)
            implementation(libs.koin.androidx.compose)
            implementation(libs.ktor.client.okhttp)
            implementation(libs.core.splashscreen)
//            implementation("com.airbnb.android:lottie:6.4.0")
            implementation("androidx.compose.ui:ui-android:1.8.2")
            implementation("com.google.firebase:firebase-auth-ktx:22.1.1")
            implementation("com.google.firebase:firebase-firestore-ktx:24.9.1")
            implementation("io.ktor:ktor-client-okhttp:2.3.2")
            implementation(libs.play.services.auth)
            implementation(libs.bundles.credential.manager)

        }
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            implementation(libs.androidx.lifecycle.viewmodelCompose)
            implementation(libs.androidx.lifecycle.runtimeCompose)
            implementation("androidx.navigation:navigation-compose:2.8.0")
            implementation("cafe.adriel.voyager:voyager-navigator:1.0.0-rc06")
            implementation("cafe.adriel.voyager:voyager-transitions:1.0.0-rc06")
            implementation("androidx.compose.material:material-icons-extended:1.7.0")
            implementation(project.dependencies.platform("androidx.compose:compose-bom:2024.08.00"))
            implementation("androidx.compose.material:material-icons-core")
            implementation("org.jetbrains.androidx.navigation:navigation-compose:2.9.0-beta04")
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            implementation(libs.androidx.lifecycle.viewmodelCompose)
            implementation(libs.androidx.lifecycle.runtimeCompose)
            implementation(libs.koin.core)
            implementation(libs.kotlinx.serialization.json)
            implementation(libs.bundles.ktor)
            api(libs.koin.core)
            implementation(libs.koin.compose)
            implementation(libs.koin.compose.viewmodel)
            implementation("dev.gitlive:firebase-auth:2.3.0")
            implementation("dev.gitlive:firebase-firestore:2.3.0")
            implementation("org.jetbrains.compose.material:material-icons-extended:1.7.0")
            implementation(libs.coil.compose.core)
            implementation(libs.coil.mp)
            implementation(libs.coil.network.ktor)
            implementation(libs.coil.compose)
            implementation(libs.coil.svg)
            implementation("io.ktor:ktor-client-core:2.3.2")
            implementation("io.ktor:ktor-client-content-negotiation:2.3.2")
            implementation("io.ktor:ktor-serialization-kotlinx-json:2.3.2")
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
        nativeMain.dependencies {
            implementation(libs.ktor.client.darwin)
        }
    }
}

android {
    namespace = "org.kmp.simfan"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "org.kmp.simfan"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    debugImplementation(compose.uiTooling)
//    implementation("com.airbnb.android:lottie-compose:6.4.0")

}