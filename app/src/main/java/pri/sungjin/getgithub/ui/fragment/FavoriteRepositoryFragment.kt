package pri.sungjin.getgithub.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint
import pri.sungjin.getgithub.databinding.FavoriteRepositoryFragmentBinding

@AndroidEntryPoint
class FavoriteRepositoryFragment: Fragment() {

    lateinit var binding: FavoriteRepositoryFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        binding = FavoriteRepositoryFragmentBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.executePendingBindings()

        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }
}