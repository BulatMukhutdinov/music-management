package tat.mukhutdinov.musicmanagement.home.di

import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import tat.mukhutdinov.musicmanagement.home.ui.HomeLifecycleAwareViewModel

object HomeInjectionModule {

    val module = module {

        viewModel {
            HomeLifecycleAwareViewModel()
        }
    }
}