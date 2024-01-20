plugins {
    id("com.android.application")
}

android {
    namespace = "com.example.ag_analizi"
    compileSdk = 34


    defaultConfig {
        applicationId = "com.example.ag_analizi"
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
    externalNativeBuild {
        cmake {
            path = file("src/main/cpp/CMakeLists.txt")
            version = "3.22.1"
        }
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    implementation("com.google.android.material:material:1.2.0-alpha02")
    implementation("com.makeramen:roundedimageview:2.3.0")
    implementation("androidx.navigation:navigation-fragment-ktx:2.2.0-rc03")
    implementation("androidx.navigation:navigation-ui-ktx:2.2.0-rc03")
    implementation("me.grantland:autofittextview:0.2.1")
    implementation("info.hoang8f:android-segmented:1.0.6")
    implementation("androidx.recyclerview:recyclerview:1.3.2")
    implementation("com.github.bumptech.glide:glide:4.13.2")
    implementation("com.android.volley:volley:1.2.1")
    //implementation("com.squareup.retrofit2:retrofit:2.9.0")
    //implementation("org.apache.httpcomponents:httpclient:4.5")
   // implementation("com.android.support:appcompat-v7:25.3.1")
}