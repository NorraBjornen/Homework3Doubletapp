package com.example.homework3doubletapp.presentation

import android.content.Context
import androidx.room.Room
import com.example.data.Database
import com.example.data.HabitApi
import com.example.data.HabitDao
import com.example.data.RepositoryImpl
import com.example.domain.Repository
import com.example.domain.usecases.DeleteHabitUseCase
import com.example.domain.usecases.GetHabitsUseCase
import com.example.domain.usecases.LoadHabitsUseCase
import com.example.domain.usecases.SaveHabitUseCase
import com.example.homework3doubletapp.R
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.Dispatchers
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class HabitsModule {

    @Singleton
    @Provides
    fun provideLoadHabitsUseCase(repository: Repository): LoadHabitsUseCase {
        return LoadHabitsUseCase(repository, Dispatchers.IO)
    }

    @Singleton
    @Provides
    fun provideSaveHabitUseCase(repository: Repository): SaveHabitUseCase {
        return SaveHabitUseCase(repository, Dispatchers.IO)
    }

    @Singleton
    @Provides
    fun provideGetHabitsUseCase(repository: Repository): GetHabitsUseCase {
        return GetHabitsUseCase(repository, Dispatchers.IO)
    }

    @Singleton
    @Provides
    fun provideDeleteHabitUseCase(repository: Repository): DeleteHabitUseCase {
        return DeleteHabitUseCase(repository, Dispatchers.IO)
    }

    @Singleton
    @Provides
    fun provideRepository(habitApi: HabitApi, habitDao: HabitDao): Repository {
        return RepositoryImpl(habitApi, habitDao)
    }

    @Singleton
    @Provides
    fun provideHabitApi(retrofit: Retrofit) : HabitApi {
        return retrofit.create(HabitApi::class.java)
    }

    @Singleton
    @Provides
    fun provideHabitDao(context: Context): HabitDao {
        return Room.databaseBuilder(
            context,
            Database::class.java, "db"
        ).fallbackToDestructiveMigration().build().habitDao()
    }

    @Singleton
    @Provides
    fun provideRetrofit(context: Context): Retrofit {
        val interceptor = Interceptor { chain ->
            val originalRequest = chain.request()
            val builder = originalRequest.newBuilder().header(
                "Authorization",
                context.resources.getString(R.string.token)
            )
            val newRequest = builder.build()

            proceed(chain, newRequest)
        }

        val okHttpClient = okhttp3.OkHttpClient().newBuilder().addInterceptor(interceptor).build()

        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(context.resources.getString(R.string.base_url))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun proceed(chain: Interceptor.Chain, request: Request) : Response {
        return try {
            chain.proceed(request)
        } catch (e: Exception) {
            e.printStackTrace()
            Thread.sleep(3000)
            proceed(chain, request)
        }
    }

}