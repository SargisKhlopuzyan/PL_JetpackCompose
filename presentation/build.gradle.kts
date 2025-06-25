plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    // This is for passing data between destinations by navigation Component
//    alias(libs.plugins.safe.args) apply false
}

android {
    namespace = "com.sargis.khlopuzyan.presentation"
    compileSdk = 35

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = "11"
    }

    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(projects.domain)
    implementation(projects.commonUi)

    // PL_JetpackCompose - PERMISSIONS
    implementation(libs.accompanist.permissions)

    // PL_JetpackCompose NAVIGATION
    implementation(libs.androidx.navigation.compose)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)

    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.material.icons.extended.android)

    // Coil
    implementation(libs.coil.compose)
    implementation(libs.coil.network.okhttp)

    // Extended Icons
//    implementation(libs.material.icons.extended)

    // Serialization
//    implementation(libs.kotlinx.serialization.json)

    // Injection Koin
    implementation(libs.koin.android)
    implementation(libs.koin.androidx.compose)
    implementation (libs.koin.androidx.workmanager)
    implementation (libs.koin.androidx.compose.navigation)

    testImplementation(libs.junit)
    testImplementation(libs.google.truth.v11)

    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    androidTestImplementation(libs.google.truth.v11)

    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}