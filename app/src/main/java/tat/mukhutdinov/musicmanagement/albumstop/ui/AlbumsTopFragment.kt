package tat.mukhutdinov.musicmanagement.albumstop.ui

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.albums_top.albums
import kotlinx.android.synthetic.main.albums_top.loading
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import tat.mukhutdinov.musicmanagement.R
import tat.mukhutdinov.musicmanagement.albumstop.ui.adapter.AlbumDiffUtilCallback
import tat.mukhutdinov.musicmanagement.albumstop.ui.adapter.AlbumsTopAdapter
import tat.mukhutdinov.musicmanagement.databinding.AlbumsTopBinding
import tat.mukhutdinov.musicmanagement.infrastructure.common.ui.BaseFragment
import tat.mukhutdinov.musicmanagement.infrastructure.util.ui.observeViewState
import tat.mukhutdinov.musicmanagement.infrastructure.util.ui.toast

class AlbumsTopFragment : BaseFragment<AlbumsTopViewModelBinding, AlbumsTopBinding>() {

    override val viewModel: AlbumsTopViewModel by viewModel<AlbumsTopLifecycleAwareViewModel> { parametersOf(args) }

    override val layoutId: Int = R.layout.albums_top

    private val args: AlbumsTopFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAlbumsList()

        viewModel.navigateUp.observe(viewLifecycleOwner, Observer { navigateUp() })

        viewModel.directions.observe(viewLifecycleOwner, Observer { navigateTo(it) })
    }

    private fun setupAlbumsList() {
        val adapter = AlbumsTopAdapter(viewModel.binding)

        albums.adapter = adapter
        albums.layoutManager = LinearLayoutManager(context)
        albums.setHasFixedSize(true)

        viewModel.albums.observeViewState(
            owner = viewLifecycleOwner,
            dataCallback = {
                val albumDiffUtilCallback = AlbumDiffUtilCallback(adapter.albums, it)
                val diffResult = DiffUtil.calculateDiff(albumDiffUtilCallback)

                adapter.albums.clear()
                adapter.albums.addAll(it)
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
                context?.toast(it.localizedMessage)
            }
        )
    }
}