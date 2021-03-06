package pri.sungjin.getgithub.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.scopes.FragmentScoped
import pri.sungjin.getgithub.api.GitHubAPI
import pri.sungjin.getgithub.database.FavoriteRepository
import pri.sungjin.getgithub.ui.adapter.FavoriteRepositoryListAdapter
import pri.sungjin.getgithub.ui.adapter.GitRepositoryListAdapter

@InstallIn(FragmentComponent::class)
@Module
object FragmentModule {

    @Provides
    @FragmentScoped
    fun provideGitRepositoryListAdapter(favoriteRepository: FavoriteRepository): GitRepositoryListAdapter {
        return GitRepositoryListAdapter(favoriteRepository = favoriteRepository)
    }

    @Provides
    @FragmentScoped
    fun provideFavoriteRepositoryListAdapter(favoriteRepository: FavoriteRepository): FavoriteRepositoryListAdapter {
        return FavoriteRepositoryListAdapter(favoriteRepository = favoriteRepository)
    }
}