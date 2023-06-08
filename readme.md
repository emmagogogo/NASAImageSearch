## Which Library you used and why you chose them?

    1. Jetpack Compose
        - Jetpack Compose is a modern UI toolkit for building Android user interfaces.
        - With With Jetpack Compose, UIs using composable functions can be build.<br>These functions are modular and reusable, making it easier to manage complex UI hierarchies.
        - It simplifies the UI development with concise and expressive UI code.
        - Provide real-time previewer

    2. Retrofit
        - Retrofit is for making network requests and handling APIs.
        - Retrofit makes it straightforward and simple to connect your Android app to RESTful APIs.

    3. Moshi
        - Moshi is a modern JSON parsing and serialization library for the Java and Kotlin.
        - It has simple API and easy to use for JSON data handling
        - It is lightweigh and effcient

    4. Navigation Compose
        -  The Navigation Compose can be used you can use to define <br> and handle navigation within your Jetpack Compose-based Android application.
        -  Declarative navigation simplifies the navigation setup and maintenance

## An Overview of how your app is architected?

    - I followed the MVVM pattern

    - Model: The **NASAImage.kt** class represents the model in my app. <br> It encapsulates the data and properties of a NASA image retrieved from the API.
    - View: The **NASAImageDetailScreen.kt** and **NASAImageSearchScreen.kt** are the Composable screens <br >that handle the UI logic and present the visual representation of the data to the user.

    - ViewModel: The **NASAImageSearchViewModel.kt** acts as the ViewModel. It contains the business logic, manages the state of the UI, and interacts <br> with the **NASAApiService** to fetch the search results. <br> The ViewModel exposes the necessary data and methods to the View for display and user interaction.

## How to build /run your app?

    1.  Start Android Studio.
    2. In the Welcome to Android Studio window, click Open.
    3.  In the file browser, navigate to where the unzipped project folder is located (likely in your Downloads folder).
    4.  Double-click on that project folder.
    5.  Wait for Android Studio to open the project.
    6.  Click the Run button to build and run the app. Make sure it builds as expected.

## Anything Else Important

    - I didn't do the paging. Instead, I used LazyColumn in Jetpack Compose to make when user scrolls through the list, it dynamically adds and removes items, which helps in managing memory efficiently, especially when dealing with large datasets.

    - I followed some codelab tutorials to do this android app. They are very useful.
        - [1](https://developer.android.com/courses/android-basics-compose/unit-1)
        - [2](https://developer.android.com/courses/android-basics-compose/course)
        - [3](https://developer.android.com/codelabs/basic-android-kotlin-training-getting-data-internet#0)
