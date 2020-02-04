package com.arjunhegde.kotlin_movie

import android.app.Application
import android.util.Log
import com.arjunhegde.kotlin_movie.data.MovieRepository
import com.arjunhegde.kotlin_movie.data.MovieRepositoryImpl
import com.arjunhegde.kotlin_movie.network.ListMovies
import com.arjunhegde.kotlin_movie.network.MovieDBClientService
import com.arjunhegde.kotlin_movie.presenter.MainPresenter
import com.arjunhegde.kotlin_movie.ui.activity.MainActivity
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.qualifier.named
import org.koin.dsl.module

class MoviesApplication : Application() {

    override fun onCreate() {
        super.onCreate()
Log.d("App","App");
        startKoin{
            androidLogger()
            androidContext(this@MoviesApplication)
            modules(appModule)
        }
    }

    private val appModule = module {
        single { MovieDBClientService() }
        single<MovieRepository> { MovieRepositoryImpl(get()) }
        single { ListMovies(get()) }

        scope(named<MainActivity>()) {
            scoped { MainPresenter(get()) }
        }
    }
}