plugins {
    alias(libs.plugins.android.application)
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.devdeveloper.pagingapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.devdeveloper.pagingapp"
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)


    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.11.0")
    implementation("com.google.code.gson:gson:2.11.0")
    implementation("com.squareup.retrofit2:converter-gson:2.11.0")

    // Rx java with retrofit
    implementation("com.squareup.retrofit2:adapter-rxjava3:2.11.0")


    // Paging Library
    implementation("androidx.paging:paging-runtime:3.3.4")
    implementation("androidx.paging:paging-rxjava3:3.3.4")


    // Hilt - Dagger
    implementation("com.google.dagger:hilt-android:2.52")
    annotationProcessor("com.google.dagger:hilt-compiler:2.52")


    // Glide Library
    implementation ("com.github.bumptech.glide:glide:4.16.0")


    // ViewModel
    implementation("androidx.lifecycle:lifecycle-viewmodel:2.8.7")
    // LiveData
    implementation("androidx.lifecycle:lifecycle-livedata:2.8.7")
    implementation("androidx.lifecycle:lifecycle-extensions:2.2.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.7")


}