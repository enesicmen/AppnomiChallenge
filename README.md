## Your comments / remarks

Enes İÇMEN
enesicmen66@gmail.com
https://www.linkedin.com/in/enesicmen/
https://github.com/enesicmen
https://github.com/enesicmen/AppnomiChallenge

## Used technologies
- Language: Kotlin
- Architecture: MVVM, Live Data, Single Activity Architecture and Navigation Component
- Dependency Injection: Dagger Hilt
- Networking: OkHttp and Retrofit
- Serialization: Gson
- Image Loading: Picasso

## Code Structure
- Data: Data Layer. It Manages data operations like fetching, saving etc..
- DI -> Dependency Injection Layer. It contains modules and qualifiers of Hilt.
- UI: UI Layer. ıt contains operations like showing/creating activites, fragments, views etc.
- Util -> Contains utility files of whole project like DateUtils.

## Data Flow
- Data is wrapped on the app as Resource<T> to be aware of data and its state like Loading, Success, Error.
- Fragment observes a live data Resource<T> data inside its ViewModel.
  Makes UI operations like showing loading, error or data when livedata changed.
- ViewModel fetches SingleLiveEvent<Resource<T>> from Repository and updates the live data with collected data with NetworkCallback
- Repository makes api requests to fetch data from API and then notifies view model over network callback parameter.

**How did you approach the task?**

- I placed API data on BuildConfigFields like BaseUrl, API_KEY key and ALIAS_KEY.
- I created kotlin extensions for some common operation to use them all app like loading image from network, showing html text etc.
- I used version control while creating the task. You can find the project on GitHub:
  https://github.com/enesicmen/AppnomiChallenge
  
 ## Preview Video
 
- https://user-images.githubusercontent.com/59308801/190964383-ed0a81a5-acea-4610-8337-1f4e5d5c87ce.mp4


