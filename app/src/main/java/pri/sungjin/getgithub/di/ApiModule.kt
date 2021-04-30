package pri.sungjin.getgithub.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import pri.sungjin.getgithub.api.GitHubAPI
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object ApiModule {

    @Provides
    @Singleton
    fun provideGitHubApi(): GitHubAPI {
        return Retrofit.Builder()
            .baseUrl(GitHubAPI.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GitHubAPI::class.java)
    }


}