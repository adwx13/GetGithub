package pri.sungjin.getgithub.activity

import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import pri.sungjin.getgithub.R
import pri.sungjin.getgithub.databinding.ActivityMainBinding
import pri.sungjin.getgithub.ui.adapter.MainFragmentAdapter
import pri.sungjin.getgithub.util.RxEventBus
import pri.sungjin.getgithub.viewmodel.BaseBindingComponent
import pri.sungjin.getgithub.viewmodel.MainViewModel
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    lateinit var binding: ActivityMainBinding
    val viewModel by viewModels<MainViewModel>()

    @Inject
    lateinit var fragmentAdapter: MainFragmentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main, BaseBindingComponent())
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        binding.executePendingBindings()

        setSupportActionBar(binding.toolbar)

        binding.viewPager.isUserInputEnabled = false
        binding.viewPager.adapter = fragmentAdapter
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            if (position == 0) {
                tab.setText(R.string.search)
            } else {
                tab.setText(R.string.favorite)
            }
        }.attach()
    }

}