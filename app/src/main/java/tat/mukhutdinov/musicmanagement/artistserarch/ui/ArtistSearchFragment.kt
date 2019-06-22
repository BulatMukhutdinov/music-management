package tat.mukhutdinov.musicmanagement.artistserarch.ui

import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.artist_search.artists
import kotlinx.android.synthetic.main.artist_search.loading
import kotlinx.android.synthetic.main.artist_search.query
import org.koin.android.viewmodel.ext.android.viewModel
import tat.mukhutdinov.musicmanagement.R
import tat.mukhutdinov.musicmanagement.artistserarch.ui.adapter.ArtistDiffUtilCallback
import tat.mukhutdinov.musicmanagement.artistserarch.ui.adapter.ArtistSearchAdapter
import tat.mukhutdinov.musicmanagement.databinding.ArtistSearchBinding
import tat.mukhutdinov.musicmanagement.home.ui.HomeFragment
import tat.mukhutdinov.musicmanagement.infrastructure.common.ui.BaseFragment
import tat.mukhutdinov.musicmanagement.infrastructure.util.ui.observeViewState
import tat.mukhutdinov.musicmanagement.infrastructure.util.ui.toast

class ArtistSearchFragment : BaseFragment<ArtistSearchViewModelBinding, ArtistSearchBinding>() {

    override val viewModel: ArtistSearchViewModel by viewModel<ArtistSearchLifecycleAwareViewModel>()

    override val layoutId: Int = R.layout.artist_search

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupSearch()

        setupArtistsList()

        viewModel.directions.observe(viewLifecycleOwner, Observer {
            (parentFragment as HomeFragment).navigateTo(it)
        })
    }

    private fun setupArtistsList() {
        val adapter = ArtistSearchAdapter(viewModel.binding)

        artists.adapter = adapter
        artists.layoutManager = LinearLayoutManager(context)

        viewModel.artists.observeViewState(
            owner = viewLifecycleOwner,
            dataCallback = {
                val artistDiffUtilCallback = ArtistDiffUtilCallback(adapter.artists, it)
                val diffResult = DiffUtil.calculateDiff(artistDiffUtilCallback)

                adapter.artists.clear()
                adapter.artists.addAll(it)
                diffResult.dispatchUpdatesTo(adapter)
            },
            loadingCallback = {
                if (it) {
                    loading.show()
                } else {
                    loading.hide()
                }
            },
            errorCallback = {
                context?.toast("${it.localizedMessage} ${it} ${it.cause?.message}")
            }
        )
    }

    private fun setupSearch() {
        query.setOnEditorActionListener { view, actionId: Int, event: KeyEvent? ->
            if (event?.keyCode == KeyEvent.KEYCODE_ENTER || actionId == EditorInfo.IME_ACTION_SEARCH) {
                viewModel.onSearchClicked(view)
                true
            } else {
                false
            }
        }
    }
}