package tat.mukhutdinov.musicmanagement.infrastructure.util.data

import androidx.annotation.MainThread
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import timber.log.Timber

class DataStateLiveData<T>(
    private val isDataOneShot: Boolean = false,
    private val isLoadingOneShot: Boolean = false,
    private val isErrorOneShot: Boolean = false,
    private val isCompleteOneShot: Boolean = false
) : MutableLiveData<DataState<T>>() {

    private var state: DataState<T>? = null

    @MainThread
    override fun observe(owner: LifecycleOwner, observer: Observer<in DataState<T>>) {
        if (hasActiveObservers()) {
            Timber.w("Multiple observers registered but only one will be notified of changes.")
        }

        // Observe the internal MutableLiveData
        super.observe(owner, Observer { viewState ->
            if (!isStateOneShot(viewState) || viewState !== state) {
                state = viewState
                observer.onChanged(viewState)
            }
        })
    }

    fun onNext(data: T) {
        value = DataState.success(data)
    }

    fun onError(throwable: Throwable) {
        value = DataState.error(throwable, value?.data)
    }

    fun onComplete() {
        value = DataState.complete(value?.data)
    }

    fun onStart() {
        value = DataState.loading(value?.data)
    }

    private fun isStateOneShot(state: DataState<T>) =
        when (state.state) {
            State.Loading -> isLoadingOneShot
            State.Success -> isDataOneShot
            State.Complete -> isCompleteOneShot
            is State.Error -> isErrorOneShot
        }

    companion object {
        fun <T> create() = DataStateLiveData<T>()

        fun <T> createForSingle() = DataStateLiveData<T>(
            isDataOneShot = true,
            isCompleteOneShot = true,
            isErrorOneShot = true,
            isLoadingOneShot = true
        )
    }
}