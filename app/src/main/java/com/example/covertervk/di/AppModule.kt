package com.example.covertervk.di

import android.provider.SyncStateContract
import com.example.covertervk.data.remote.Api
import com.example.covertervk.data.repository.RepositoryImpl
import com.example.covertervk.domain.repository.Repository
import com.example.covertervk.domain.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideExchangeApi(): Api {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Api::class.java)
    }

    @Provides
    @Singleton
    fun provideValueRepository(api: Api): Repository {
        return RepositoryImpl(api)
    }
}