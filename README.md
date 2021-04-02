# Android Application Using MVVM

## Using Daggar-2, coroutines, JetPack & Retrofit
![](https://img.shields.io/badge/android-Kotlin-yellowgreen) ![](https://img.shields.io/badge/Dependency%20Injection%20-Dagger-blue)

### Application Short Description:
 This Application is just to demonstrate the MMVM Architecture simply. The application has a list of comments that are shown in Recyclerview. I am trying to use some famous jetpack and android architectural components on a basic or intermediate level for better understanding. 
The Application will be covering the following components and libraries.

### Topics Covered
- MVVM Architecture.
- Dagger 2 for dependency injection.
- Coroutines for asynchronous stuff.
- Retrofit for API calls.
- DataBinding Library Android
- Paging Library for pagination
- Unit testing and UI testing

#### Sample Codes
I am providing some coding samples as well for  `batter understandings`

#### Gradle dependencies
	dependencies {
		/* Kotlin Support  */
		implementation "org.jetbrains.kotlin:kotlin-stdlib:$kotlin_version"
		implementation 'androidx.core:core-ktx:1.3.2'
		implementation 'androidx.appcompat:appcompat:1.2.0'
		implementation 'com.google.android.material:material:1.3.0'
		implementation 'androidx.constraintlayout:constraintlayout:2.0.4'
		implementation 'androidx.navigation:navigation-fragment-ktx:2.3.4'
		implementation 'androidx.navigation:navigation-ui-ktx:2.3.4'
		/* paging dependency  */
		implementation "androidx.paging:paging-runtime-ktx:2.1.2"

		/* Coroutines dependency  */
		implementation 'org.jetbrains.kotlinx:kotlinx-coroutines-core:1.4.2'

		/* Retrofit using , Okhttp, Okhttp logging interceptor, Gson  */
		//Retrofit
		implementation 'com.squareup.retrofit2:retrofit:2.9.0'
		implementation 'com.google.code.gson:gson:2.8.6'
		implementation 'com.squareup.retrofit2:converter-gson:2.5.0'
		implementation 'com.squareup.okhttp3:logging-interceptor:3.14.9'

		/* dagger dependency  */
		implementation 'com.google.dagger:dagger-android:2.20'
		implementation 'com.google.dagger:dagger-android-support:2.20'
		// if you use the support libraries
		kapt 'com.google.dagger:dagger-android-processor:2.20'
		kapt 'com.google.dagger:dagger-compiler:2.20'

		/* Unit Testing Support  */
		testImplementation 'junit:junit:4.+'
		androidTestImplementation 'androidx.test.ext:junit:1.1.2'
		androidTestImplementation 'androidx.test.espresso:espresso-core:3.3.0'
	}

*** Still under Develpemnt
       


