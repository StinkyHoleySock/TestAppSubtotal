package com.example.testappsubtotal.di

import com.example.testappsubtotal.data.repository.BooksRepository
import com.example.testappsubtotal.data.repository.BooksRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindRepository(
        repositoryImpl: BooksRepositoryImpl
    ) : BooksRepository
}