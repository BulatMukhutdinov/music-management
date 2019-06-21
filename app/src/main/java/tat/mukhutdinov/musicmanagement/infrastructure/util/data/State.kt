package tat.mukhutdinov.musicmanagement.infrastructure.util.data

sealed class State {

    object Loading : State()

    object Success : State()

    object Complete : State()

    class Error(val throwable: Throwable) : State()
}