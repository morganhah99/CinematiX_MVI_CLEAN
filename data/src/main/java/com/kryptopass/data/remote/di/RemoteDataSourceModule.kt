package com.kryptopass.data.remote.di

import com.kryptopass.data.remote.source.RemoteMovieDataSourceImpl
import com.kryptopass.data.repo.remote.RemoteMovieDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RemoteDataSourceModule {

    @Binds
    abstract fun bindCompanyInfoDataSource(datasource: RemoteMovieDataSourceImpl): RemoteMovieDataSource
}