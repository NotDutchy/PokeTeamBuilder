package com.example.poketeambuilder.di

import android.content.Context
import androidx.room.Room
import com.example.poketeambuilder.R
import com.example.poketeambuilder.data.dao.PokeDao
import com.example.poketeambuilder.data.database.AppDatabase
import com.example.poketeambuilder.data.datasources.LocalPokeDataSource
import com.example.poketeambuilder.data.datasources.LocalPokeDataSourceImpl
import com.example.poketeambuilder.data.datasources.RemotePokeDataSource
import com.example.poketeambuilder.data.datasources.RemotePokeDataSourceImpl
import com.example.poketeambuilder.data.network.PokeApiService
import com.example.poketeambuilder.data.repos.PokeRepository
import com.example.poketeambuilder.data.repos.PokeRepositoryImpl
import com.example.poketeambuilder.domain.usecases.GetAllPokeUseCase
import com.example.poketeambuilder.domain.usecases.GetFilteredPokeUseCase
import com.example.poketeambuilder.ui.viewmodels.DetailedPokeCardViewModel
import com.example.poketeambuilder.ui.viewmodels.PokeDexViewModel
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, "Pokemon-database").allowMainThreadQueries().fallbackToDestructiveMigration()
            .build()

    @Provides
    @Singleton
    fun provideDao(db: AppDatabase): PokeDao = db.pokemonDao()

    @Provides
    @Singleton
    fun provideRetrofit(@ApplicationContext context: Context): Retrofit =
        Retrofit.Builder().baseUrl(context.getString(R.string.api_baseurl))
            .addConverterFactory(GsonConverterFactory.create()).build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): PokeApiService =
        retrofit.create(PokeApiService::class.java)

    @Provides
    @Singleton
    fun provideRemoteDataSource(
        pokeApiService: PokeApiService,
        @ApplicationContext context: Context
    ): RemotePokeDataSource = RemotePokeDataSourceImpl(pokeApiService, context)

    @Provides
    @Singleton
    fun provideLocalDataSource(
        pokeDao: PokeDao,
        @ApplicationContext context: Context
    ) : LocalPokeDataSource = LocalPokeDataSourceImpl(pokeDao, context)

    @Provides
    @Singleton
    fun providePokeRepository(
        remotePokeDataSource: RemotePokeDataSource,
        localPokeDataSource: LocalPokeDataSource
    ) : PokeRepository = PokeRepositoryImpl(remotePokeDataSource, localPokeDataSource)

    @Provides
    @Singleton
    fun provideGetAllPokeUseCase(repo: PokeRepository): GetAllPokeUseCase = GetAllPokeUseCase(repo)

    @Provides
    @Singleton
    fun provideGetFilteredPokemon(repo: PokeRepository): GetFilteredPokeUseCase = GetFilteredPokeUseCase(repo)

    @Provides
    @Singleton
    fun providePokeDexViewModel(getAllUseCase: GetAllPokeUseCase, getFilteredUseCase: GetFilteredPokeUseCase): PokeDexViewModel = PokeDexViewModel(getAllUseCase, getFilteredUseCase)

    @Provides
    @Singleton
    fun provideDetailedPokeCardViewmodel(): DetailedPokeCardViewModel = DetailedPokeCardViewModel()
}