plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    id("androidx.navigation.safeargs")
    kotlin("plugin.serialization") version "1.7.0"
    id("com.google.devtools.ksp")
}

android {
    namespace = "psb.test.currencyconverter"
    compileSdk = 34

    defaultConfig {
        applicationId = "psb.test.currencyconverter"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures{
        viewBinding = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    val nav_version = "2.7.5"

    // Kotlin
    implementation("androidx.navigation:navigation-fragment-ktx:$nav_version")
    implementation("androidx.navigation:navigation-ui-ktx:$nav_version")


// architecture components
    val coreVersion = "1.7.0"
    val lifecycleVersion = "2.4.0"
    val pagingVersion = "3.1.0"
    implementation ("androidx.core:core-ktx:$coreVersion")
    implementation ("androidx.lifecycle:lifecycle-runtime-ktx:$lifecycleVersion")
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVersion")
    implementation ("androidx.lifecycle:lifecycle-livedata-ktx:$lifecycleVersion")
    implementation ("androidx.lifecycle:lifecycle-viewmodel-savedstate:$lifecycleVersion")
    implementation ("androidx.paging:paging-runtime-ktx:$pagingVersion")

    val retrofitVersion = "2.9.0"
    val okhttpLoggingInterceptorVersion = "4.9.0"
    val moshiVersion = "1.12.0"
    // retrofit
    implementation ("com.squareup.retrofit2:retrofit:$retrofitVersion")
    implementation ("com.squareup.retrofit2:converter-moshi:$retrofitVersion")
    implementation ("com.squareup.moshi:moshi:$moshiVersion")
    implementation ("com.squareup.moshi:moshi-kotlin:$moshiVersion")
    implementation ("com.squareup.retrofit2:retrofit-mock:$retrofitVersion")
    implementation ("com.squareup.okhttp3:logging-interceptor:$okhttpLoggingInterceptorVersion")
    implementation ("com.squareup.retrofit2:converter-gson:$retrofitVersion")

    implementation ("com.google.android.gms:play-services-location:21.0.1")

    implementation ("com.fasterxml.jackson.module:jackson-module-kotlin:2.13.3")
    implementation ("com.google.code.gson:gson:2.9.0")
    implementation("org.json:json:20220320")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.3")
    testImplementation(kotlin("test"))

    //RoomDao
    val room_version = "2.6.0"

    implementation("androidx.room:room-runtime:$room_version")
    annotationProcessor("androidx.room:room-compiler:$room_version")

    // To use Kotlin annotation processing tool (kapt)
    ksp("androidx.room:room-compiler:$room_version")
    // To use Kotlin Symbol Processing (KSP)
    ksp("androidx.room:room-compiler:$room_version")

    //View binding delegate
    implementation ("com.github.kirich1409:viewbindingpropertydelegate-noreflection:1.5.6")

    //Glide
    implementation ("com.github.bumptech.glide:glide:4.14.2")
    annotationProcessor ("com.github.bumptech.glide:compiler:4.14.2")
}