plugins {
	id("com.android.application")
	id("org.jetbrains.kotlin.android")
	kotlin("kapt")
	id(Hilt.plugin)
}

android {
	compileSdk = Android.compileSdk
	buildToolsVersion = "33.0.2"
	namespace = "com.looker.droidify"
	defaultConfig {
		applicationId = Android.appId
		minSdk = Android.minSdk
		targetSdk = Android.compileSdk
		versionCode = Android.versionCode
		versionName = Android.versionName
		vectorDrawables.useSupportLibrary = true

		resourceConfigurations += mutableListOf(
			/* locale list begin */
			"ar",
			"az",
			"bg",
			"bn",
			"ca",
			"cs",
			"de",
			"el",
			"es",
			"fa",
			"fi",
			"fr",
			"gl",
			"hi",
			"hr",
			"hu",
			"in",
			"it",
			"iw",
			"ja",
			"kn",
			"ko",
			"lt",
			"lv",
			"ml",
			"nb-rNO",
			"nl",
			"nn",
			"or",
			"pa",
			"pl",
			"pt",
			"pt-rBR",
			"ro",
			"ru",
			"si",
			"sv",
			"tl",
			"tr",
			"uk",
			"vi",
			"zh-rCN",
			"zh-rTW"
			/* locale list end */
		)
	}

	sourceSets.forEach { source ->
		val javaDir = source.java.srcDirs.find { it.name == "java" }
		source.java {
			srcDir(File(javaDir?.parentFile, "kotlin"))
		}
	}

	compileOptions {
		isCoreLibraryDesugaringEnabled = true

		sourceCompatibility = JavaVersion.VERSION_11
		targetCompatibility = JavaVersion.VERSION_11
	}

	kotlinOptions.jvmTarget = "11"

	buildTypes {
		getByName("debug") {
			applicationIdSuffix = ".debug"
			resValue("string", "application_name", "Droid-ify-Debug")
		}
		getByName("release") {
			isMinifyEnabled = true
			isShrinkResources = true
			resValue("string", "application_name", "Droid-ify")
			proguardFiles(
				getDefaultProguardFile("proguard-android-optimize.txt"),
				"proguard.pro"
			)
		}
		create("alpha") {
			initWith(getByName("debug"))
			applicationIdSuffix = ".alpha"
			resValue("string", "application_name", "Droid-ify Alpha")
			isDebuggable = true
			isMinifyEnabled = true
		}
		all {
			buildConfigField(
				type = "String",
				name = "VERSION_NAME",
				value = "\"v${Android.versionName}\""
			)
		}
	}
	packagingOptions {
		jniLibs {
			excludes += Excludes.jniExclude
		}
		resources {
			excludes += Excludes.listExclude
		}
	}
	buildFeatures {
		viewBinding = true
		aidl = false
		renderScript = false
		shaders = false
	}
	composeOptions {
		kotlinCompilerExtensionVersion = "1.4.4"
	}

}

dependencies {

	coreLibraryDesugaring(AndroidX.desugar)

	implementation(project(Modules.coreModel))
	implementation(project(Modules.coreCommon))
	implementation(project(Modules.coreDatastore))
	implementation(project(Modules.installer))

	implementation(composeBom())

	implementation(kotlin("stdlib"))
	implementation(Core.core)

	implementation(AndroidX.appCompat)
	implementation(AndroidX.material)
	implementation(AndroidX.recyclerView)

	compose()

	implementation(Coil.coil)

	implementation(Coroutines.core)
	implementation(Coroutines.android)

	implementation(Hilt.android)
	implementation(Hilt.work)
	kapt(Hilt.compiler)
	kapt(Hilt.androidX)

	implementation(Jackson.core)

	implementation(Kotlin.datetime)

	implementation(Lifecycle.fragment)
	implementation(Lifecycle.activity)
	implementation(Lifecycle.runtime)

	implementation(OkHttp.okhttp)

	implementation(Others.zoomage)

	implementation(SQLite.ktx)

	implementation(Work.manager)
}