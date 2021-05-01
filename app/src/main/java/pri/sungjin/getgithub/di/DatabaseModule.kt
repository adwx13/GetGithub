package pri.sungjin.getgithub.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import pri.sungjin.getgithub.database.AppDatabase
import pri.sungjin.getgithub.database.FavoriteRepository
import pri.sungjin.getgithub.ui.DATABASE_NAME
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(appContext, AppDatabase::class.java, DATABASE_NAME).build()
    }

    @Provides
    @Singleton
    fun provideFavoriteReposDao(database: AppDatabase): FavoriteRepository {
        return database.favoriteReposDao()
    }
}