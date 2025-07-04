// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.kotlinter) apply false

    // This is for passing data between destinations by navigation Component
//    alias(libs.plugins.safe.args) apply false

//    alias(libs.plugins.licensee) apply false
}