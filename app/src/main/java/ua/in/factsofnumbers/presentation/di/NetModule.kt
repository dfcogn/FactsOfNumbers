package ua.`in`.factsofnumbers.presentation.di

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import ua.`in`.factsofnumbers.BuildConfig
import ua.`in`.factsofnumbers.data.api.NumberService
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetModule(private val baseUrl: String) {
    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit{
        val interceptor = HttpLoggingInterceptor().apply {
            this.level = HttpLoggingInterceptor.Level.BODY
        }

        val client = OkHttpClient.Builder().apply {
            if(BuildConfig.DEBUG){
                addInterceptor(interceptor)
            }

            connectTimeout(15, TimeUnit.SECONDS)
            writeTimeout(15, TimeUnit.SECONDS)
            readTimeout(15, TimeUnit.SECONDS)
        }.build()

        return Retrofit.Builder().addConverterFactory(ScalarsConverterFactory.create()).client(client)
            .baseUrl(baseUrl).build()
    }

    @Singleton
    @Provides
    fun provideNumberService(retrofit: Retrofit): NumberService{
        return retrofit.create(NumberService::class.java)
    }
}