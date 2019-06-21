package tat.mukhutdinov.musicmanagement.infrastructure.common.ui.adapter

import android.view.View
import androidx.annotation.CallSuper
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.recyclerview.widget.RecyclerView

open class BaseViewHolder<T>(view: View) : RecyclerView.ViewHolder(view), LifecycleOwner {

    override fun getLifecycle(): Lifecycle = lifecycleRegistry

    private val lifecycleRegistry by lazy { LifecycleRegistry(this) }

    @CallSuper
    open fun bindTo(item: T?) {
        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_START)
    }

    @CallSuper
    open fun onViewRecycled() {
        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_STOP)
    }
}