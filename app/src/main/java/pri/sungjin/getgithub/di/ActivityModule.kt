package pri.sungjin.getgithub.di

import androidx.fragment.app.FragmentActivity
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped
import pri.sungjin.getgithub.api.GitHubAPI
import pri.sungjin.getgithub.ui.adapter.MainFragmentAdapter


@InstallIn(ActivityComponent::class)
@Module
object ActivityModule {
    @Provides
    @ActivityScoped
    fun provideMainFragmentAdapter(fragmentActivity: FragmentActivity): MainFragmentAdapter {
        return MainFragmentAdapter(fragmentActivity)
    }

}