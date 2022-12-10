package com.madpickle.buildsrc

object Libs{

    object Kotlin{
        const val stdLib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8"
    }

    object Android {
        const val core = "androidx.core:core-ktx:1.8.0"
        const val appCompat = "androidx.appcompat:appcompat:1.4.2"
        const val material = "com.google.android.material:material:1.7.0-alpha02"
        const val constraintLayout = "androidx.constraintlayout:constraintlayout:2.1.4"

    }

    object Lifecycle {
        private const val version = "2.4.1"
        const val savedState = "androidx.lifecycle:lifecycle-viewmodel-savedstate:$version"
        const val liveData = "androidx.lifecycle:lifecycle-livedata-ktx:$version"
        const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:$version"
        const val service = "androidx.lifecycle:lifecycle-service:$version"
    }

    object Navigation {
        private const val version = "2.5.0"
        const val navFragment = "androidx.navigation:navigation-fragment-ktx:$version"
        const val navUi =  "androidx.navigation:navigation-ui-ktx:$version"
        const val navDynamic = "androidx.navigation:navigation-dynamic-features-fragment:$version"
    }

    object Coil {
        const val coilKt = "io.coil-kt:coil:1.3.2"
    }

    object Test {
        const val truthTest = "com.google.truth:truth:1.1.3"
        const val junit = "junit:junit:4.13.2"
        const val junitExt = "androidx.test.ext:junit:1.1.3"
        const val espresso = "androidx.test.espresso:espresso-core:3.4.0"
    }

    object Logs {
        const val timber = "com.jakewharton.timber:timber:5.0.1"
    }

    object Dagger{
        private const val daggerVersion = "2.42"
        const val dagger = "com.google.dagger:dagger:$daggerVersion"
        const val daggerCompiler = "com.google.dagger:dagger-compiler:$daggerVersion"
    }

    object RxJava{
        private const val rxAndroidVersion = "3.0.0"
        private const val rxJavaVersion = "3.1.4"
        private const val rxKotlinVersion = "3.0.1"
        const val rxAndroid = "io.reactivex.rxjava3:rxandroid:$rxAndroidVersion"
        const val rxJava = "io.reactivex.rxjava3:rxjava:$rxJavaVersion"
        const val rxKotlin = "io.reactivex.rxjava3:rxkotlin:$rxKotlinVersion"
    }

    object Realm {
        const val base = "io.realm:realm-gradle-plugin:10.10.1"
    }

    object Retrofit {
        private const val version = "2.9.0"
        const val lib = "com.squareup.retrofit2:retrofit:$version"
        const val jsonParser = "com.squareup.retrofit2:converter-gson:$version"
        const val converter = "com.squareup.retrofit2:converter-scalars:$version"
        const val okHttp = "com.squareup.okhttp3:okhttp:4.9.3"
        const val rxAdapterRetrofit = "com.squareup.retrofit2:adapter-rxjava3:$version"
        const val okHttpLogging = "com.squareup.okhttp3:logging-interceptor:4.7.2"
    }

    object WorkManager{
        private const val version = "2.7.1"
        const val lib = "androidx.work:work-runtime-ktx:$version"
        const val testing = "androidx.work:work-testing:$version"
        const val multiprocess = "androidx.work:work-multiprocess:$version"
    }

}