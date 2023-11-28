Login App
===================

Приложение с возможностью авторизации пользователя и вывода списка его платежей после успешной авторизации

Используется [API] "https://easypay.world"

### Архитектура

* [MVVM](https://developer.android.com/jetpack/guide)
* [Kotlin 100%](https://kotlinlang.org/)
* [Coroutines](https://github.com/Kotlin/kotlinx.coroutines)
* [Flow](https://kotlinlang.org/docs/flow.html)

### Preview

<p align="center">
<img src="Login_gif.gif" width="32%"/>
</p>
<p align="left">
<img src="Pic_1.jpg" width="32%"/>
<img src="Pic_2.jpg" width="32%"/>
<img src="Pic_3.jpg" width="32%"/>
</p>

### Библиотеки

* Di
    * [Koin](https://github.com/InsertKoinIO/koin)
* Retrofit2 & OkHttp3
    * [Converter: Gson](https://mvnrepository.com/artifact/com.squareup.retrofit2/converter-gson)
    * [Retrofit](https://mvnrepository.com/artifact/com.squareup.retrofit2/retrofit)
    * [OkHttp Logging Interceptor](https://mvnrepository.com/artifact/com.squareup.okhttp3/logging-interceptor)
* Navigation
    * Single Activity
    * [Navigation component](https://developer.android.google.cn/guide/navigation/navigation-getting-started?hl=en)
* Other
    * [Core Kotlin Extensions](https://developer.android.com/kotlin/ktx#core)
