# Iono view-githup-task
 web app that displaying list of the most starred Github repos that were created in the last 30 days
 <br/>
  <br/>
   <br/>


# Techicality 

The project uses Clean architecture as structure pattern consisting of the layers : 
*  Data layer contains data source logic-networking via Retrofit-, Data Transfer Objects, and repositories implementation 
*  Domina layer contains mainly project Bussiness rules, and consist of Domain Named Objects, Repositories, Mappers, and application use cases 
*  Presentation layer contains UI related and dependent Apis, it contains The, Components, Navigation, ViewModels, UI State, UI Intent, and framework Apis-Activities..etc



# Dependencies 


* Retorift : type-safe REST client for Android and Java which aims to make it easier to consume RESTful web services
* coroutines : a concurrency design pattern that you can use on Android to simplify code that executes asynchronously.
* Dagger Hilt : a dependency injection library for Android that reduces the boilerplate of doing manual dependency injection
* Glide : an image loading library for Android backed by Kotlin Coroutines
* MVVM :uisng a Jetpack libraries view model and live data  
* Basic Recycler view paging

# Tests 


* Use cases test: create a test for every use case i depend on at the project,create a test for the important logic parts
* ui test : add just the needed ui test to ensure that every thing is working.
 
 <br/>
  <br/>
   <br/>

<img src= "https://github.com/abdelrhmanelgendy/Ionoview-githup-task/blob/feature/githup-most-starred/screenShoots/Screenshot_20220424-103219_GitHup%20Task.jpg" width="300">
<img src= "https://github.com/abdelrhmanelgendy/Ionoview-githup-task/blob/feature/githup-most-starred/screenShoots/Screenshot_20220424-103234_GitHup%20Task.jpg" width="300">
<img src= "https://github.com/abdelrhmanelgendy/Ionoview-githup-task/blob/feature/githup-most-starred/screenShoots/Screenshot_20220424-103240_GitHup%20Task.jpg" width="300">
 
