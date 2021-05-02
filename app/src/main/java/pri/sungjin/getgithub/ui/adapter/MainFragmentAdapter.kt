package pri.sungjin.getgithub.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import pri.sungjin.getgithub.api.GitHubAPI
import pri.sungjin.getgithub.ui.fragment.FavoriteRepositoryFragment
import pri.sungjin.getgithub.ui.fragment.GitSearchFragment

class MainFragmentAdapter(fragmentActivity: FragmentActivity): FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return if (position == 0) {
            GitSearchFragment()
        } else {
            FavoriteRepositoryFragment()
        }
    }
}