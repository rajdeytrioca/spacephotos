# Spacephotos
Sample app which uses the public NASA api to search images and displayes them

# App Architecture

## Single Activity Design
### Uses 3 fragment for Search, Search Results and Details View

#### I have made the app modular as much as possible. Network, Common, Corefeatures are the modules created.

1. DI : Koin (Lightweight dependency injection library which works great wit Kotlin)
2. Image Loading : Glide (Very reliable image loading library. This is used to load the images from the Uri from the api response)
3. Retrofit for network calls(Obvious choice for making network calls)
4. Jetpack Navigation (I have 3 fragment, and jetpack navigation makes it very easy to have the transition between them.)
5. MVVM (Each fragment supporrted by Viewmodels, Mainactivity has its own viewmodel)


![Screenshot_1650337126](https://user-images.githubusercontent.com/16020469/163911540-83a6b069-1f0c-46ed-ab6b-42ae9e69c764.png)![Screenshot_1650337283](https://user-images.githubusercontent.com/16020469/163911893-ec4061ef-dc03-4d8a-a407-2d88918bd3a4.png)![Screenshot_1650337526](https://user-images.githubusercontent.com/16020469/163912149-ddb6fb60-9977-429c-a5ec-14193c5fea00.png)
