package com.example.driversapptest.presentation.di

import com.example.driversapptest.BuildConfig
import com.example.driversapptest.data.BASE_URL
import com.example.driversapptest.data.GoodsService
import com.example.driversapptest.domain.interactor.GetGoodsUseCase
import com.example.driversapptest.domain.interactor.SetPrealertPrintUseCase
import com.example.driversapptest.presentation.IPresenter
import com.example.driversapptest.presentation.MainPresenter
import com.example.driversapptest.presentation.MainView
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule {

    companion object {
        private const val CONNECT_TIMEOUT_SECONDS = 120L
        private const val READ_TIMEOUT_SECONDS = 120L
    }

    @Singleton
    @Provides
    fun provideRestaurantService(okHttpClient: OkHttpClient): GoodsService {
        val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        return retrofit.create(GoodsService::class.java)
    }

    @Singleton
    @Provides
    fun provideMainPresenter(
            getGoodsUseCase: GetGoodsUseCase,
            setPrealertUseCase: SetPrealertPrintUseCase,
            dispatcher: DispatchersProvider
    ): IPresenter<MainView> {
        return MainPresenter(
                getGoodsUseCase = getGoodsUseCase,
                setPrealertPrintUseCase = setPrealertUseCase,
                dispatchersProvider = dispatcher
        )
    }

    @Singleton
    @Provides
    fun provideSetPrealertPrintUseCase(goodsService: GoodsService): SetPrealertPrintUseCase {
        return SetPrealertPrintUseCase(goodsService)
    }

    @Singleton
    @Provides
    fun provideGetGoodsUseCase(goodsService: GoodsService): GetGoodsUseCase {
        return GetGoodsUseCase(goodsService)
    }


    @Singleton
    @Provides
    fun provideOkHttpClient(
            httpLoggingInterceptor: HttpLoggingInterceptor,
            headerInterceptor: HeaderInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .addInterceptor(headerInterceptor)
                .connectTimeout(CONNECT_TIMEOUT_SECONDS, TimeUnit.SECONDS)
                .readTimeout(READ_TIMEOUT_SECONDS, TimeUnit.SECONDS)
                .build()
    }

    @Singleton
    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.level = if (BuildConfig.DEBUG)
            HttpLoggingInterceptor.Level.BODY
        else
            HttpLoggingInterceptor.Level.NONE
        return logging
    }

    @Singleton
    @Provides
    fun provideHeaderInterceptor(): HeaderInterceptor {
        return HeaderInterceptor()
    }
}