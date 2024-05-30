package com.kryptopass.cinematix.di

import com.kryptopass.data.repo.MovieRepositoryImpl
import com.kryptopass.data.repo.local.LocalMovieDataSource
import com.kryptopass.data.repo.remote.RemoteMovieDataSource
import com.kryptopass.domain.repo.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    fun provideMovieRepository(
        remoteSource: RemoteMovieDataSource,
        localSource: LocalMovieDataSource
    ): MovieRepository = MovieRepositoryImpl(
        remoteSource,
        localSource
    )
}