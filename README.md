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
- Work Manager
- Locale Notifications
- Unit testing and UI testing Expresso

#### Sample Codes
I am providing some coding samples as well for  `batter understandings`

#### Gradle dependencies
```grovy
	  dependencies {
	    //For MultiDex Enable True as app is using lot of libs and size is expanded
	    implementation 'androidx.multidex:multidex:2.0.1'

	    //To Use WorkManager Android "JetPack"
	    implementation "androidx.work:work-runtime-ktx:2.5.0"

	    /* Core Libs and Kotlin Support  */
	    implementation "org.jetbrains.kotlin:kotlin-stdlib:$kotlin_version"
	    implementation 'androidx.core:core-ktx:1.3.2'
	    implementation 'androidx.appcompat:appcompat:1.2.0'
	    implementation 'com.google.android.material:material:1.3.0'
	    implementation 'androidx.constraintlayout:constraintlayout:2.0.4'

	    //Navigation Library
	    implementation 'androidx.navigation:navigation-fragment-ktx:2.3.4'
	    implementation 'androidx.navigation:navigation-ui-ktx:2.3.4'

	    /* Paging Library dependency  */
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
	    implementation 'androidx.legacy:legacy-support-v4:1.0.0'

	    // if you use the support libraries
	    kapt 'com.google.dagger:dagger-android-processor:2.20'
	    kapt 'com.google.dagger:dagger-compiler:2.20'

	    /* Unit Testing Support  */
	    androidTestImplementation 'androidx.test:rules:1.3.0-beta01'
	    androidTestImplementation 'androidx.test.espresso:espresso-contrib:3.4.0-alpha05'
	    testImplementation 'junit:junit:4.13.2'
	    androidTestImplementation 'androidx.test.espresso:espresso-core:3.3.0'
	    androidTestImplementation 'androidx.test.ext:junit:1.1.2'
	}
```
### Using Coroutines Sample:
Android coroutines are used to executes code asynchronously. I used it for calling Apis. Here is the code for that.
Add dependancy:
	
	/* Coroutines dependency  */
	implementation 'org.jetbrains.kotlinx:kotlinx-coroutines-core:1.4.2'
Here is the method from where I initiated it.
```kotlin
private fun loadData() {
        loader.visibility = View.VISIBLE
        GlobalScope.launch(Dispatchers.Main) {
            viewModel.getPagedCommentsList()
                ?.observe(viewLifecycleOwner, Observer { pagedList ->
                    requireActivity().runOnUiThread {
                        adapter.submitList(pagedList)
                    }
                })
        }
    }
```
Sample with async:
```kotlin
suspend fun getComments(): List<CommentsModel> {
	val comments = GlobalScope.async(Dispatchers.IO) { repo.getComments(pageNumber) }
	return comments.await()
}
```

### WorkManager:
Work Manager is an android API for doing long tasks like download files, or any other tasks that need to be done periodically. I used it just to show Local notifications after some time periodically. Here is the code of that.
Add this dependancy: 
	
	//To Use WorkManager Android "JetPack"
	implementation "androidx.work:work-runtime-ktx:2.5.0"
Created worker class as follow:
```kotlin
    class ShowPushNotificationTask(val context: Context, workerParams: WorkerParameters) :
        Worker(context, workerParams) {

        override fun doWork(): Result {
            AppUtils.showNotification(
                context,
                inputData.getStringArray("title")?.firstOrNull(),
                inputData.getStringArray("desc")?.firstOrNull()
            )
            return Result.success()
        }
    }
```
and to initiate worker thread used this code:
```kotlin	
    private fun setNotificationAfterEvery3Seconds(title: String, desc: String) {
        val data = Data.Builder()
        data.putString("title", title)
        data.putString("desc", desc)

        val periodicWorkRequest = PeriodicWorkRequest.Builder(
            ShowPushNotificationTask::class.java,
            10,
            TimeUnit.MILLISECONDS,
            2,
            TimeUnit.MILLISECONDS
        )
            .setInitialDelay(2, TimeUnit.MILLISECONDS)
            .setInputData(data.build())
            .build()
        WorkManager.getInstance().enqueue(periodicWorkRequest);
    }	  
```

### Local Notifications:
I also used local notification in the app for demo purposes. Have a look at its code that I added in UtilsClass
```kotlin
object AppUtils {
        private val CHANNEL_ID: String = "com.intsab.com"
        fun showNotification(context: Context, title: String?, desc: String?) {
            createNotificationChannel(context)
            val builder = NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(title)
                .setContentText(desc)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setSmallIcon(R.drawable.ic_launcher_background)

            with(NotificationManagerCompat.from(context)) {
                notify(Random.nextInt(), builder.build())
            }
        }

        private fun createNotificationChannel(context: Context) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val name: CharSequence = "LocalNotification"
                val description = "its a dummy description"
                val importance = NotificationManager.IMPORTANCE_DEFAULT
                val channel = NotificationChannel(
                    CHANNEL_ID, name,
                    importance
                )
                channel.description = description
                val notificationManager: NotificationManager = context.getSystemService(
                    NotificationManager::class.java
                )
                notificationManager.createNotificationChannel(channel)
            }
        }
    }
```

### Retrofit
Used Retrofit for API's call here is the sample code for that
Use following dependencies in Gradle
	
	/* Retrofit using , Okhttp, Okhttp logging interceptor, Gson  */
	//Retrofit
	implementation 'com.squareup.retrofit2:retrofit:2.9.0'
	implementation 'com.google.code.gson:gson:2.8.6'
	implementation 'com.squareup.retrofit2:converter-gson:2.5.0'
	implementation 'com.squareup.okhttp3:logging-interceptor:3.14.9'
Created Data Source as following:

```kotlin
	class RemoteDataSource  @Inject constructor(){
	    fun getComments(url: String): Call<List<CommentsModel>> {
		return provideCommentsRetrofitService().getComments(url)
	    }

	    private fun provideCommentsRetrofitService(): CommentsService {
		val client = OkHttpClient().newBuilder().addInterceptor(HttpLoggingInterceptor()).build()
		val retro = Retrofit.Builder()
		    .baseUrl("https://jsonplaceholder.typicode.com/")
		    .client(client)
		    .addConverterFactory(GsonConverterFactory.create())
		    .build()
		return retro.create(CommentsService::class.java)
	    }
	}
```
Here is the Service Interface
```kotlin
	interface CommentsService {
	    @GET
	    fun getComments(@Url url:String): Call<List<CommentsModel>>
	}
```
	
### Android Navigation Library
Used Latest Android Navigation graph library for navigation between fragments and activities. Here is the sample for that.
Dependancy for this lib is:
	
	//Navigation Library
	implementation 'androidx.navigation:navigation-fragment-ktx:2.3.4'
	implementation 'androidx.navigation:navigation-ui-ktx:2.3.4'
Here is the navigation hrarchiy
```xml
	<?xml version="1.0" encoding="utf-8"?>
	<navigation xmlns:android="http://schemas.android.com/apk/res/android"
	    xmlns:app="http://schemas.android.com/apk/res-auto"
	    android:id="@+id/nav_graph"
	    app:startDestination="@id/mainActivityFragment">

	    <fragment
		android:id="@+id/mainActivityFragment"
		android:name="com.intsab.mvvm.fragments.MainActivityFragment"
		android:label="MainActivityFragment">
		<action
		    android:id="@+id/action_mainActivityFragment_to_detailsFragment"
		    app:destination="@id/detailsFragment" />
	    </fragment>
	    <fragment
		android:id="@+id/detailsFragment"
		android:name="com.intsab.mvvm.fragments.DetailsFragment"
		android:label="DetailsFragment">
		<argument
		    android:name="name"
		    android:defaultValue="null"
		    app:argType="string" />
		<argument
		    android:name="email"
		    android:defaultValue="null"
		    app:argType="string" />
		<argument
		    android:name="details"
		    android:defaultValue="null"
		    app:argType="string" />
	    </fragment>
	</navigation>
```

### Data Binding Library
Using DataBinding to set Data within the views here is a sample for that.
```xml
	<?xml version="1.0" encoding="utf-8"?>
	<layout xmlns:android="http://schemas.android.com/apk/res/android"
	    xmlns:app="http://schemas.android.com/apk/res-auto"
	    xmlns:tools="http://schemas.android.com/tools">

	    <data>

		<variable name="name" type="String" />
		<variable name="email" type="String" />
		<variable name="description" type="String" />
	    </data>

	    <androidx.constraintlayout.widget.ConstraintLayout
		android:layout_width="match_parent"
		android:layout_height="wrap_content"
		android:paddingStart="16dp"
		android:paddingTop="16dp"
		android:paddingEnd="16dp">

		<TextView
		    android:id="@+id/tv_name"
		    android:layout_width="match_parent"
		    android:layout_height="wrap_content"
		    android:text="@{name}"
		    android:textColor="@color/black"
		    android:textSize="26sp"
		    android:textStyle="bold"
		    app:layout_constraintEnd_toEndOf="parent"
		    app:layout_constraintStart_toStartOf="parent"
		    app:layout_constraintTop_toTopOf="parent"
		    tools:text="name" />

		<TextView
		    android:id="@+id/tv_email"
		    android:layout_width="match_parent"
		    android:layout_height="wrap_content"
		    android:layout_marginTop="4dp"
		    android:text="@{email}"
		    android:textColor="@android:color/holo_blue_dark"
		    android:textSize="24sp"
		    app:layout_constraintEnd_toEndOf="parent"
		    app:layout_constraintStart_toStartOf="parent"
		    app:layout_constraintTop_toBottomOf="@+id/tv_name"
		    tools:text="email" />

		<TextView
		    android:id="@+id/tv_comment"
		    android:layout_width="match_parent"
		    android:layout_height="wrap_content"
		    android:layout_marginTop="8dp"
		    android:text="@{description}"
		    android:textColor="@color/black"
		    android:textSize="24sp"
		    app:layout_constraintEnd_toEndOf="parent"
		    app:layout_constraintStart_toStartOf="parent"
		    app:layout_constraintTop_toBottomOf="@+id/tv_email"
		    tools:text="description" />


	    </androidx.constraintlayout.widget.ConstraintLayout>
	</layout>
```

### Dagger 2
Used Dagger 2 for dependency Injection here is the code example for that
Dependancies:
	
	/* dagger dependency  */
	 implementation 'com.google.dagger:dagger-android:2.20'
	 implementation 'com.google.dagger:dagger-android-support:2.20'
	 implementation 'androidx.legacy:legacy-support-v4:1.0.0'

	// if you use the support libraries
	kapt 'com.google.dagger:dagger-android-processor:2.20'
	kapt 'com.google.dagger:dagger-compiler:2.20'
	
My APplication Class:
```kotlin	
	class MyApplication: Application() {
	    val appComponent = DaggerApplicationComponent.create()
	}
```
Inject constructors like this
```kotlin
	class Repository @Inject constructor(private val remoteDataSource: RemoteDataSource) {}
```

### Paging Library
Used android Paging Library for lazy loading of list data here is the example for that.
added Dependency 
	 
	 /* Paging Library dependency  */
	 implementation "androidx.paging:paging-runtime-ktx:2.1.2"
Here is the code for adapter:
```kotlin	
	class CommentsAdapter(val listener: (item: CommentsModel) -> Unit) : PagedListAdapter<CommentsModel, CommentsAdapter.CommentsViewHolder>(CommentsModel.CALLBACK) {

	    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentsViewHolder {
		val layoutInflater = LayoutInflater.from(parent.context)
		val itemBinding: CommentItemLayoutBinding =
		    CommentItemLayoutBinding.inflate(layoutInflater, parent, false)
		return CommentsViewHolder(itemBinding)
	    }

	    override fun onBindViewHolder(holder: CommentsViewHolder, position: Int) {
		holder.bind(getItem(position)!!)
		holder.itemView.setOnClickListener {
		    listener.invoke(getItem(position)!!)
		}
	    }

	    class CommentsViewHolder(private val binding: CommentItemLayoutBinding) :
		RecyclerView.ViewHolder(binding.root) {
		fun bind(obj: CommentsModel) {
		    binding.apply {
			comment = obj
			executePendingBindings()
		    }
		}
	    }
	}
```

### Expresso UI Testing
Tested full UI using Expresso Testing here is the test cases for that.
```kotlin
	@LargeTest
	@RunWith(AndroidJUnit4::class)
	class MainActivityTest {

	    @Rule
	    @JvmField
	    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

	    private var decorView: View? = null

	    @Before
	    fun loadDecorView() {
		decorView = mActivityTestRule.activity.window.decorView
	    }

	    @Test
	    fun mainActivityTest() {
		onView(
		    withId(R.id.loader)
		).check(matches(isDisplayed()))

		try {
		    Thread.sleep(4000)
		} catch (e: InterruptedException) {
		    e.printStackTrace()
		}

		onView(
		    withId(R.id.loader)
		).check(matches(not(isDisplayed())))


		val recyclerView = onView(
		    allOf(
			withId(R.id.recyclerView),
			childAtPosition(
			    withClassName(`is`("androidx.constraintlayout.widget.ConstraintLayout")),
			    1
			)
		    )
		)
		recyclerView.perform(actionOnItemAtPosition<ViewHolder>(0, click()))
		onView(withText("Clicked")).inRoot(RootMatchers.withDecorView(not(decorView)))
		    .check(matches(isDisplayed()))
		onView(
		    allOf(
			withId(R.id.tv_name),
			isDisplayed()
		    )
		).check(matches(withText("id labore ex et quam laborum")))

		onView(
		    allOf(
			withId(R.id.tv_email),
			isDisplayed()
		    )
		).check(matches(withText("Eliseo@gardner.biz")))
	    }

	    private fun childAtPosition(
		parentMatcher: Matcher<View>, position: Int
	    ): Matcher<View> {

		return object : TypeSafeMatcher<View>() {
		    override fun describeTo(description: Description) {
			description.appendText("Child at position $position in parent ")
			parentMatcher.describeTo(description)
		    }

		    public override fun matchesSafely(view: View): Boolean {
			val parent = view.parent
			return parent is ViewGroup && parentMatcher.matches(parent)
				&& view == parent.getChildAt(position)
		    }
		}
	    }
	}
```

