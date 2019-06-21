package tat.mukhutdinov.musicmanagement.infrastructure.common.ui

interface BaseViewModel<T : BaseViewModelBinding> {

    val binding: T
}
