package tat.mukhutdinov.musicmanagement.infrastructure.common.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import tat.mukhutdinov.musicmanagement.BR

abstract class BaseFragment<T : BaseViewModelBinding, B : ViewDataBinding> : Fragment() {

    protected abstract val viewModel: BaseViewModel<T>

    protected abstract val layoutId: Int

    protected lateinit var binding: B

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, layoutId, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = viewLifecycleOwner

        binding.setVariable(BR.binding, viewModel.binding)
    }

    fun navigateTo(action: NavDirections) {
        view?.findNavController()?.navigate(action)
    }

    fun navigateUp() {
        view?.findNavController()?.navigateUp()
    }
}