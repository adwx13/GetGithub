package pri.sungjin.getgithub.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.scopes.FragmentScoped
import pri.sungjin.getgithub.ui.adapter.GitRepositoryListAdapter

@InstallIn(FragmentComponent::class)
@Module
object FragmentModule {

    @Provides
    @FragmentScoped
    fun provideGitRepositoryListAdapter(): GitRepositoryListAdapter {
        return GitRepositoryListAdapter()
    }
}