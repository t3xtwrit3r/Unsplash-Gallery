# Unsplash-Gallery
I have used Kotlin and MVVM architectural design pattern, I have also used Dagger-Hilt for Dependency Injection. For Navigation I have used the android navigation component.
To make an infinite scrollable gallery I've used Staggered Grid Layout Manager with RecyclerView and paginated api calling which is triggered by scrolling.
By clicking on Infinite RecyclerView image item, user will be able to view the image in Full-Screen mood with zoom in and zoom out feature.
User can share or save to Gallery the viewed image item from Full-Screen view page.
Sharing the image requires Storage Read/Write permission as the bitmap needs to be extracted first to get the Uri from the image download URL.
I have used 5 Megabytes of Retrofit API cache (Internet Connection is required for first time use only, after that user can view cache data if no Internet).
I have used Glide library's automatic Diskcache Strategy to cache an Image data when Viewed in Full-Screen. Once user has opened an image in Full-Screen then it can be viewed again without internet connection.

//Extra Feature that could be implemented if there were more time
I also could add some extra features like offline Data sync using Room local database and a periodic Work Manager. 
the periodic Work Manager will fetch some pages each day at a particular time and store them to local database accordingly. user will view the gallery from local storage. 
Another feature could be saving Images as Favourite into Room local database and view them later from a Favourite section.

//P:S: Couldn't solve this problem:

The AbstractCompile.destinationDir property has been deprecated. This is scheduled to be removed in Gradle 8.0. Please use the destinationDirectory property instead. Consult the upgrading guide for further information: https://docs.gradle.org/7.3.3/userguide/upgrading_version_7.html#compile_task_wiring
at org.gradle.api.tasks.compile.AbstractCompile.setDestinationDir(AbstractCompile.java:113)
at org.gradle.api.tasks.compile.JavaCompile_Decorated.setDestinationDir(Unknown Source)
at dagger.hilt.android.plugin.HiltGradlePlugin$configureVariantAggregatingTask$componentsJavaCompileTask$1.execute(HiltGradlePlugin.kt:390)
at dagger.hilt.android.plugin.HiltGradlePlugin$configureVariantAggregatingTask$componentsJavaCompileTask$1.execute(HiltGradlePlugin.kt:56)