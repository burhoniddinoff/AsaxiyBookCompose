// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    dependencies {
        classpath(libs.kotlin.gradle.plugin)
        classpath(libs.google.services)
    }
}

plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.jetbrainsKotlinAndroid) apply false

    id("com.google.dagger.hilt.android") version "2.50" apply false
    id("com.google.gms.google-services") version "4.4.1" apply false
}