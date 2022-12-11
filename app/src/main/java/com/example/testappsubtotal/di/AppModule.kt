package com.example.testappsubtotal.di

import com.example.testappsubtotal.data.api.BookApiService
import com.example.testappsubtotal.data.repository.BooksRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideApi(): BookApiService {
        return Retrofit.Builder()
            .baseUrl("https://www.googleapis.com")
            .build()
            .create(BookApiService::class.java)
    }

    @Provides
    @Singleton
    fun providesRepository(service: BookApiService): BooksRepositoryImpl {
        return BooksRepositoryImpl(service)
    }
}