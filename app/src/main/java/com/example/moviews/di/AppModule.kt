package com.example.moviews.di

import android.content.Context
import androidx.datastore.core.DataStore
import com.example.moviews.login.AuthRepository
import com.example.moviews.login.AuthRepositoryImpl
import com.example.moviews.repository.MovieRepository
import com.example.moviews.service.MovieApiService
import com.example.moviews.util.Constrants.BASE_MOVIE_URL
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.prefs.Preferences
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Singleton
    @Provides
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Singleton
    @Provides
    fun providesAuthRepository(impl: AuthRepositoryImpl): AuthRepository = impl


    @Singleton
    @Provides
    fun provideMovieRepository(
        api : MovieApiService
    ) = MovieRepository(api)

    @Singleton
    @Provides
    fun provideMovieApi() : MovieApiService{
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_MOVIE_URL)
            .build()
            .create(MovieApiService::class.java)
    }

}