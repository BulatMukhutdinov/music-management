package tat.mukhutdinov.musicmanagement.infrastructure.util.ui

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import tat.mukhutdinov.musicmanagement.infrastructure.util.data.DataState

fun <DataType> LiveData<DataState<DataType>>.observeViewState(
    owner: LifecycleOwner,
    dataCallback: ((DataType) -> Unit)? = null,
    errorCallback: ((Throwable) -> Unit)? = null,
    completeCallback: (() -> Unit)? = null,
    loadingCallback: ((Boolean) -> Unit)? = null
) {
    this.observe(owner, Observer { dataState ->
        dataState?.either(dataCallback, errorCallback, completeCallback, loadingCallback)
    })
}

fun Context.toast(text: String): Toast = Toast
    .makeText(this, text, Toast.LENGTH_LONG)
    .apply { show() }