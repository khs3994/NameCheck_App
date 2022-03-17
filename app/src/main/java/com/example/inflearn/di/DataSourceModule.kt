package com.example.inflearn.di

import com.example.data.remote.api.LoveCalculatorApi
import com.example.data.repository.remote.datasource.MainDataSource
import com.example.data.repository.remote.datasource.SplashDataSource
import com.example.data.repository.remote.datasoureimpl.MainDataSourceImpl
import com.example.data.repository.remote.datasoureimpl.SplashDataSourceImpl
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataSourceModule {

    @Provides
    @Singleton
    fun provideMainDataSource(
        loveCalculatorApi: LoveCalculatorApi,
        firebaseRtdb: FirebaseDatabase,
        firestore: FirebaseFirestore
    ): MainDataSource {
        return MainDataSourceImpl(
            loveCalculatorApi,
            firebaseRtdb,
            firestore
        )
    }


    @Provides
    @Singleton
    fun provideSplashDataSource(
        firebaseRtdb: FirebaseDatabase,
        firestore: FirebaseFirestore
    ): SplashDataSource {
        return SplashDataSourceImpl(
            firebaseRtdb, firestore
        )
    }
}