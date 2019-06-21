package tat.mukhutdinov.musicmanagement.home.ui

import androidx.lifecycle.ViewModel

class HomeLifecycleAwareViewModel : ViewModel(), HomeViewModel, HomeViewModelBinding {

    override val binding: HomeViewModelBinding = this
}