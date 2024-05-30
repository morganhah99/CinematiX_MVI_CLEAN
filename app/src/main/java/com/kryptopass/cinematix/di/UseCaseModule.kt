package com.kryptopass.cinematix.di

import com.kryptopass.domain.repo.MovieRepository
import com.kryptopass.domain.usecase.GetMovieByIdUseCase
import com.kryptopass.domain.usecase.GetMoviesUseCase
import com.kryptopass.domain.usecase.UseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Provides
    fun provideUseCaseConfiguration(): UseCase.Configuration = UseCase.Configuration(Dispatchers.IO)

    @Provides
    fun provideGetMoviesUseCase(
        configuration: UseCase.Configuration,
        repository: MovieRepository
    ): GetMoviesUseCase = GetMoviesUseCase(
        configuration,
        repository
    )

    @Provides
    fun GetMovieByIdUseCase(
        configuration: UseCase.Configuration,
        repository: MovieRepository
    ): GetMovieByIdUseCase = GetMovieByIdUseCase(
        configuration,
        repository
    )
}