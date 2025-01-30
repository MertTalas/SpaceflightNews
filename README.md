# SpaceflightNews 🚀

## 📱 About The Project

SpaceflightNews is your go-to Android application for staying updated with the latest news and developments in space exploration, rocket launches, and aerospace technology. Get real-time updates about space missions, satellite launches, and astronomical discoveries right at your fingertips.

## 🌟 Features

- Performant listing of space news and articles
- A detail page for in-depth coverage of each news item
- An easy and user-friendly design
- Pull-to-refresh feature for displaying the latest news
- Single-click web redirect for accessing news sources

## 🏗 Architecture
MVVM is the architectural pattern used to structure the app's user interface and business logic. It separates the app into three main components:

1. Model: Represents the data and business logic of the application. It handles data retrieval from the Spaceflight News APIs.
2. View: Represents the user interface. It observes changes from the ViewModel and updates the UI accordingly.
3. ViewModel: Acts as a mediator between the Model and the View. It holds and processes data, as well as handles user interactions. It also exposes data to the View via Kotlin Flows.

Clean Architecture emphasizes the separation of concerns by dividing the app into layers, each with its specific responsibilities:

1. Presentation Layer: This layer contains the MVVM components (View and ViewModel) and is responsible for rendering the user interface and handling user interactions.
2. Domain Layer: Contains the business logic and use cases. It is independent of the Presentation Layer and communicates through interfaces or contracts.
3. Data Layer: Handles data retrieval and storage. It communicates with the Domain Layer through interfaces, abstracting the data sources.
   
## 🛠 Built With

- Kotlin
- Android Jetpack Components
  - Navigation Component
  - ViewModel
  - Coroutines
  - Shimmer
- Dependency Injection - Hilt
- Retrofit for API calls
- Coil for image loading
- Material Design 3
- OkHttp and Timber for API logging
- JUnit and Mockk for Unit Testing

## 🏃‍♂️ Getting Started

### Prerequisites

- Latest version of Android Studio
- JDK 11
- Android min SDK 21
- Android target SDK 35
- Gradle 8.10.2

### Installation

1. Clone the repository
   ```bash
   git clone https://github.com/MertTalas/SpaceflightNews.git
2. Open project in Android Studio
3. Sync project with Gradle files
4. Run the app on an emulator or physical device
