package tat.mukhutdinov.musicmanagement.infrastructure.common.di

import android.util.Base64
import androidx.room.Room
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import tat.mukhutdinov.musicmanagement.infrastructure.common.db.DataBase
import tat.mukhutdinov.musicmanagement.infrastructure.common.gateway.LastFmApi
import tat.mukhutdinov.musicmanagement.infrastructure.common.gateway.boundary.ImageConverter
import tat.mukhutdinov.musicmanagement.infrastructure.common.model.ImageConverterImpl

object CommonInjectionModule {
    private const val DATABASE_NAME = "music_manager_db"
    private const val BASE_SERVER_URL = "https://ws.audioscrobbler.com/2.0/"

    val module = module {

        single {
            Room.databaseBuilder(get(), DataBase::class.java, DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build()
        }

        factory<ImageConverter> {
            ImageConverterImpl()
        }


        single<LastFmApi> {
            val httpLoggingInterceptor = HttpLoggingInterceptor()
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

            val httpClientBuilder = OkHttpClient.Builder()
            httpClientBuilder
                .addInterceptor { chain ->
                    val key = String(Base64.decode(getNativeKey().toByteArray(), Base64.DEFAULT))

                    val original = chain.request()
                    val originalHttpUrl = original.url()

                    val url = originalHttpUrl.newBuilder()
                        .addQueryParameter("format", "json")
                        .addQueryParameter("api_key", key)
                        .build()

                    val requestBuilder = original.newBuilder().url(url)

                    chain.proceed(requestBuilder.build())
                }
                .addInterceptor(httpLoggingInterceptor)

            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_SERVER_URL)
                .client(httpClientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            retrofit.create(LastFmApi::class.java)
        }
    }

    private external fun getNativeKey(): String
}